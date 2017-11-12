#!/usr/bin/env python

import os
from random import randrange as rrange
from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine, Table, Column, Integer, String, MetaData, ForeignKey
from ushuffle_db import NAMES, randName
from sqlalchemy import pool
from sqlalchemy import desc, asc  # desc
from sqlalchemy.orm import relationship

from sqlalchemy.ext.declarative import declarative_base

config = {'user': 'root',
          'password': 'password',
          'host': 'localhost',
          'port': 3306,
          'dbname': 'test'}

# create orm class base
Base = declarative_base()
DBSession = sessionmaker()

FIELDS = ('login', 'uid', 'prid')
DBNAME = 'test'
COLSIZ = 10


class User(Base):
    # table name
    __tablename__ = 'user'

    # table schema
    id = Column(Integer, primary_key=True)
    name = Column(String(20))
    addresses = relationship('Address', order_by='Address.id')

    def __init__(self, userid, username):
        self.id = userid
        self.name = username


class Address(Base):
    __tablename__ = 'addresses'
    id = Column(Integer, primary_key=True)
    email_address = Column(String(256), nullable=False)
    user_id = Column(Integer, ForeignKey('user.id'))

    user = relationship("User", back_populates="addresses")

    def __init__(self, email_address):
        self.email_address = email_address

    def __repr__(self):
        return "<Address(email_address='%s')>" % self.email_address


class MySQLAlchemy(object):
    def __init__(self, db, dbName):
        import MySQLdb
        import _mysql_exceptions
        MySQLdb = pool.manage(MySQLdb)
        url = 'mysql://db=%s' % DBNAME
        eng = create_engine(url)
        try:
            cxn = eng.connection()
        except _mysql_exceptions.OperationalError, e:
            eng1 = create_engine('mysql://user=root')
            try:
                eng1.execute('DROP DATABASE %s' % DBNAME)
            except _mysql_exceptions.OperationalError, e:
                pass
            eng1.execute('CREATE DATABASE %s' % DBNAME)
            eng1.execute(
                "GRANT ALL ON %s.* TO ''@'localhost'" % DBNAME)
            eng1.commit()
            cxn = eng.connection()

        try:
            users = Table('users', eng, autoload=True)
        except exceptions.SQLError, e:
            users = Table('users', eng,
                          Column('login', String(8)),
                          Column('uid', Integer),
                          Column('prid', Integer),
                          redefine=True)

        self.eng = eng
        self.cxn = cxn
        self.users = users

    def create(self):
        users = self.users
        try:
            users.drop()
        except exceptions.SQLError, e:
            pass
        users.create()

    def insert(self):
        d = [dict(zip(FIELDS,
                      [who, uid, rrange(1, 5)])) for who, uid in randName()]
        return self.users.insert().execute(*d).rowcount

    def update(self):
        users = self.users
        fr = rrange(1, 5)
        to = rrange(1, 5)
        return fr, to, \
               users.update(users.c.prid == fr).execute(prid=to).rowcount

    def delete(self):
        users = self.users
        rm = rrange(1, 5)
        return rm, \
               users.delete(users.c.prid == rm).execute().rowcount

    def dbDump(self):
        res = self.users.select().execute()
        print '\n%s%s%s' % ('LOGIN'.ljust(COLSIZ),
                            'USERID'.ljust(COLSIZ), 'PROJ#'.ljust(COLSIZ))
        for data in res.fetchall():
            print '%s%s%s' % tuple([str(s).title().ljust(COLSIZ) for s in data])

    def __getattr__(self, attr):
        return getattr(self.users, attr)

    def finish(self):
        self.cxn.commit()
        self.eng.commit()


def main():
    print '*** Connecting to %r database' % DBNAME

    metadata = MetaData(engine)

    user = Table('user', metadata,
                 Column('id', Integer, primary_key=True),
                 Column('name', String(20)),
                 Column('fullname', String(40)),
                 )

    address_table = Table('address', metadata,
                          Column('id', Integer, primary_key=True),
                          Column('user_id', None, ForeignKey('user.id')),
                          Column('email', String(128), nullable=False)
                          )

    metadata.create_all()


def main2():
    # create session
    url = 'mysql+pymysql://{}:{}@{}/{}'.format(
        config['user'],
        config['password'],
        config['host'],
        config['dbname'])
    engine = create_engine(url, echo=True, encoding="utf-8")

    # create table if not exists
    Base.metadata.drop_all(bind=engine)
    Base.metadata.create_all(bind=engine)

    # create session
    DBSession.configure(bind=engine)
    session = DBSession()

    # clear data
    session.query(User).delete()
    session.commit()

    # insert user
    for i in range(1, 10):
        chenchen = User(i, 'chenchen')
        session.add(chenchen)
        print chenchen.id
    session.commit()

    # query
    # case1
    for instance in session.query(User).order_by(asc(User.id)).offset(3).limit(2):
        print(instance.id, instance.name)

    # case2
    for id, name in session.query(User.id, User.name):
        print id, name

    # case 3
    print session.query(User.id, User.name).count()

    # case 4 test relation
    jack = User(99, 'jack')
    jack.addresses = [Address(email_address='tiankongcc1991@qq.com'),
                      Address(email_address='chenchen@qq.com')]

    session.add(jack)
    session.commit()

    fake_jack = session.query(User).filter(User.name == 'jack').one()
    print fake_jack.addresses

    # case 5 test join
    for u, a in session.query(User, Address).filter(User.id == Address.user_id).filter(
                    Address.email_address == 'chenchen@qq.com').all():
        print u.name, a

    # close
    session.close()


if __name__ == '__main__':
    main2()

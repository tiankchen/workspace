package com.chenchen.ch5;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@PropertySource("classpath:app.properties")
@RestController
@SpringBootApplication
public class Ch5Application {

    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @Value("${disc.title}")
    private String title;

    // @RequestMapping("/")
    // String index() {
    // return "book name is: " + bookName + " and book author is: " + bookAuthor +
    // title;
    // }

    @RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
    public String index(Model model) throws JsonGenerationException,
            JsonMappingException, IOException {
        Person single = new Person("aa", 11);

        List<Person> people = new ArrayList<Person>();
        Person p1 = new Person("xx", 11);
        Person p2 = new Person("yy", 22);
        Person p3 = new Person("zz", 33);
        people.add(p1);
        people.add(p2);
        people.add(p3);

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // mapper.writeValue(new File("country.json"), single);
        String value = mapper.writeValueAsString(single);

        return GetJsonByTreeMode();
    }

    public static void main(String[] args) {
        // SpringApplication.run(Ch5Application.class, args);
        SpringApplication app = new SpringApplication(
                Ch5Application.class);
        // app.setShowBanner(false); //not found?
        app.run(args);
    }

    private String GetJsonByTreeMode() {
        // 创建一个节点工厂,为我们提供所有节点
        JsonNodeFactory factory = new JsonNodeFactory(false);
        // 创建一个json factory来写tree modle为json
        JsonFactory jsonFactory = new JsonFactory();

        // 注意，默认情况下对象映射器不会指定根节点，下面设根节点为country
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode country = factory.objectNode();

        country.put("country_id", "China");
        country.put("birthDate", "1949-10-01");

        // 在Java中，List和Array转化为json后对应的格式符号都是"obj:[]"
        ArrayNode nation = factory.arrayNode();
        nation.add("Han").add("Meng").add("Hui").add("WeiWuEr")
                .add("Zang");
        country.set("nation", nation);

        ArrayNode lakes = factory.arrayNode();
        lakes.add("QingHai Lake").add("Poyang Lake")
                .add("Dongting Lake").add("Taihu Lake");
        country.set("lakes", lakes);

        ArrayNode provinces = factory.arrayNode();
        ObjectNode province = factory.objectNode();
        ObjectNode province2 = factory.objectNode();
        province.put("name", "Shanxi");
        province.put("population", 37751200);
        province2.put("name", "ZheJiang");
        province2.put("population", 55080000);
        provinces.add(province).add(province2);
        country.set("provinces", provinces);

        ObjectNode traffic = factory.objectNode();
        traffic.put("HighWay(KM)", 4240000);
        traffic.put("Train(KM)", 112000);
        country.set("traffic", traffic);

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        String value = null;
        try {
            value = mapper.writeValueAsString(country);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return value;
    }

}

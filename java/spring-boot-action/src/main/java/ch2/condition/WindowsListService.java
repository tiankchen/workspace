package ch2.condition;

public class WindowsListService implements ListService {

    @Override
    public String showListCmd() {
        return "dir";
    }
}

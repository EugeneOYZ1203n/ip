package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveHandlerStub extends SaveHandler {

    List<String> saveData;

    public SaveHandlerStub() {
        super("");
        saveData = new ArrayList<>();
    }

    @Override
    public void save(String[] saveStrings) {
        saveData.addAll(Arrays.asList(saveStrings));
    }

    @Override
    public String[] load() {
        return saveData.toArray(String[]::new);
    }
}

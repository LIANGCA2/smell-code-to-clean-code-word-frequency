import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {
            List<Input> inputList = groupInputWithKeyword(inputStr);
            //get the map for the next step of sizing the same word
            List<Word> resultModel = translateToModel(inputList);
            return buildResultView(resultModel);
        }
    }

    public String buildResultView(List<Word> resultModel) {
        StringJoiner inputView = new StringJoiner("\n");
        for (Word w : resultModel) {
            String s = w.getValue() + " " + w.getWordCount();
            inputView.add(s);
        }
        return inputView.toString();
    }

    public List<Word> translateToModel(List<Input> inputList) {
        Map<String, List<Input>> map = getListMap(inputList);

        List<Word> resultModel = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue().size());
            resultModel.add(word);
        }
        resultModel.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return resultModel;
    }

    public List<Input> groupInputWithKeyword(String inputStr) {
        //split the input string with 1 to n pieces of spaces
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }
        return inputList;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}

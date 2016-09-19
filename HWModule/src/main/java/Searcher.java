import java.util.ArrayList;
import java.util.Arrays;

public class Searcher implements ISearcher {

    private final int MAX_RESULT_LENGTH = 12;
    private NameData[] nameDatas;

    public void refresh(String[] classNames, long[] modificationDates) {
        final int length = classNames.length;
        nameDatas = new NameData[length];
        for (int i = 0; i < length; i++) {
            nameDatas[i] = new NameData(classNames[i], modificationDates[i]);
        }
        Arrays.parallelSort(nameDatas);
    }

    public String[] guess(String start) {
        ArrayList<String> result = new ArrayList<>(MAX_RESULT_LENGTH);

        return result.toArray(new String[result.size()]);
    }

    private class NameData implements Comparable<NameData> {
        private String name;
        private long date;

        NameData(String name, long date) {
            this.name = name;
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public long getDate() {
            return date;
        }

        @Override
        public int compareTo(NameData o) {
            if (this.date == o.date) {
                return this.name.compareTo(o.name);
            } else {
                if (this.date > o.date) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

}

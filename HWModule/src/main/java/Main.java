import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args){
        final int classNamesCount = 100000;
        String[] classNames = new String[classNamesCount];
        long[] modificationDates = new long[classNamesCount];
        for (int i = 0; i < classNamesCount; i++) {
            classNames[i] = UUID.randomUUID().toString().replace("-", "");
            modificationDates[i] = ThreadLocalRandom.current().nextLong(0, System.currentTimeMillis());
//            System.out.println(classNames[i] + " - " + modificationDates[i]);
        }

        System.out.println("----");

        ISearcher searcher = SearcherFactory.newInstance();

        long time = System.currentTimeMillis();
        searcher.refresh(classNames, modificationDates);
        System.out.println("- refresh time: " + (System.currentTimeMillis() - time));
        long avrTime = 0;
        int testCount = 10;
        for (int k = 0; k < testCount; k++) {
            String start = UUID.randomUUID().toString().replace("-", "").substring(0, 4);//1 + new Random().nextInt(4));
            System.out.println("- " + start);
            time = System.currentTimeMillis();
            final String[] guess = searcher.guess(start);
            final long time2 = System.currentTimeMillis() - time;
            avrTime += time2;
            for (String result : guess) {
                System.out.println(result);
            }
            System.out.println("----");
        }
        avrTime /= testCount;
        System.out.println("- average time: " + avrTime);
    }
}

package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PrintSortedHash<T extends  Comparable> {
    /**
     * for coding style
     */
    public PrintSortedHash() {
    }

    /**
     * sorteaza ascendent un hashmap, mai intai dupa valoare iar apoi alfabetic.
     * @param hashMap
     * @return
     */
    public List<Map.Entry<String, T>> sortHash(final Map<String, T> hashMap) {

        List<Map.Entry<String, T>> sortlist = new ArrayList<>(hashMap.entrySet());
                sortlist.sort((e1, e2) -> {
                        if (e1.getValue().compareTo(e2.getValue()) == 0) {
                            return e1.getKey().compareTo(e2.getKey());
                        }
                            return e1.getValue().compareTo(e2.getValue());
                        });
                return sortlist;
        /*String toPrint = "";
                for (int i = 0; i < sortlist.size(); i++) {
            if (!(sortlist.get(i).getValue().equals(0))) {
                toPrint = toPrint + sortlist.get(i).getKey() + ", ";
            }
        }
                if (!(toPrint.isEmpty())) {
            toPrint = toPrint.substring(0, toPrint.length() - 2);
        }
                return "Query result: [" + toPrint + "]";*/
    }
}

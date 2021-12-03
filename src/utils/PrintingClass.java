package utils;

import java.util.List;
import java.util.Map;

public class PrintingClass<T extends Comparable> {
    /**
     * for codying style
     */
    public PrintingClass() {
    }

    /**
     * aici printez funtiile
     * @param utilHashMap
     * @param number
     * @param sortType
     * @return
     */
    public String printing(final Map<String, T> utilHashMap,
                           final int number, final String sortType) {

        List<Map.Entry<String, T>> sortlist;
        if (sortType.equals("asc")) {
            PrintSortedHash<T> top = new PrintSortedHash<>();
            sortlist = top.sortHash(utilHashMap);
        } else {
            PrintReverseSortedHash<T> top = new PrintReverseSortedHash<>();
            sortlist = top.sortReverseHash(utilHashMap);
        }
        String toPrint = "";
        for (int i = 0; i < sortlist.size() && i < number; i++) {
            if (!(sortlist.get(i).getValue().equals(0))) {
                toPrint = toPrint + sortlist.get(i).getKey() + ", ";
            }
        }
        if (!(toPrint.isEmpty())) {
            toPrint = toPrint.substring(0, toPrint.length() - 2);
        }
        return toPrint;
    }
}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TelefonByFIO {
    private Map<String, String> mapTel2Fio = new HashMap<>();
    private Map<String, HashSet<String>> mapIndex = new HashMap<>();

    TelefonByFIO() {
    }

    boolean add(String phone, String fio) {
        if (mapTel2Fio.containsKey(phone)) {
            System.out.println(" " + phone + " уже есть с фио " + mapTel2Fio.get(phone));
            return false;
        }
        HashSet<String> numbers = (mapIndex.containsKey(fio))?  mapIndex.get(fio): new HashSet<>();
        numbers.add(phone);
        mapIndex.put(fio, numbers);

        mapTel2Fio.put(phone, fio);
        return true;
    }

    Set<String> get(String lastName) {
        return mapIndex.get(lastName);
    }
}

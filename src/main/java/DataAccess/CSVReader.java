package DataAccess;

import BLL.BaseProduct;
import BLL.MenuItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CSVReader {
    private final String pathName;

    public CSVReader(String pathName) {
        this.pathName = pathName;
    }

    /**
     * This method is used to read from a csv file using streams and lambda expressions
     * @return - the list as the type class MenuItem
     */
    public List<MenuItem> readCSV() throws IOException {
        List<MenuItem> baseProducts = null;
        Path path = Path.of(pathName);
        try {
            baseProducts = Files.lines(path)
                    .skip(1)
                    .map(line -> {
                        String[] values = line.split(",");
                        return new BaseProduct(values[0], Float.parseFloat(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), Integer.parseInt(values[6]));
                    })
                    .filter(distinct(p -> p.getTitle()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return baseProducts;
    }

    public static <T> Predicate<T> distinct(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}

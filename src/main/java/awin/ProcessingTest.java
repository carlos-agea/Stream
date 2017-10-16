package awin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessingTest {

    @Test
    public void processTestNumberItems() {
        Processing processing = new Processing();

        Stream inpStream = Stream.of("123,34","234,45");
        Stream outStream = processing.process(inpStream);

        Assert.assertEquals(outStream.count(), 2);

    }

    @Test(expected = RuntimeException.class)
    public void processTestNonNumberItems() {
        Processing processing = new Processing();

        Stream inpStream = Stream.of("a,34","234,45");
        Stream outStream = processing.process(inpStream);

        Assert.assertEquals(outStream.count(), 2);

    }

    @Test(expected = RuntimeException.class)
    public void processTestZeroNumbers() {
        Processing processing = new Processing();

        Stream inpStream = Stream.of("","1,34");
        Stream outStream = processing.process(inpStream);

        Assert.assertEquals(outStream.count(), 1);

    }

    @Test(expected = RuntimeException.class)
    public void processTestMoreThan30Numbers() {
        Processing processing = new Processing();

        Stream inpStream = Stream.of("1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1");
        Stream outStream = processing.process(inpStream);

        Assert.assertEquals(outStream.count(), 1);

    }

    @Test
    public void processTestNumberNumbers() {
        Processing processing = new Processing();

        Stream<String> inpStream = Stream.of("123,34","234,45");
        Stream<String> outStream = processing.process(inpStream);

        outStream.forEach( item -> {
            Assert.assertEquals(item.split(",").length,2);
        });

    }

    @Test
    public void processShouldReturnCorrectValues() throws IOException {
        // Given
        Processing processing = new Processing();
        ObjectMapper objectMapper = new ObjectMapper();
        Stream<String> inpStream = Stream.of("123,34","234,45");
        Stream<String> outStream = processing.process(inpStream);

        // When
        List<String> results = outStream.collect(Collectors.toList());

        // Verify
        ProcessResult processResult = objectMapper.readValue(results.get(0), ProcessResult.class);
        Assert.assertTrue(processResult.getSumOfValues().compareTo(157)==0);
        Assert.assertTrue(processResult.getNumberOfValues().compareTo(2)==0);

        processResult = objectMapper.readValue(results.get(1), ProcessResult.class);
        Assert.assertTrue(processResult.getSumOfValues().compareTo(279)==0);
        Assert.assertTrue(processResult.getNumberOfValues().compareTo(2)==0);
    }
}

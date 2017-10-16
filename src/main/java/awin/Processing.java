package awin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.stream.Stream;

public class Processing {

    public Stream<String> process(Stream<String> inputStream) {
        ObjectMapper objectMapper = new ObjectMapper();
        return inputStream.map(item -> {
            ProcessResult processResult = new ProcessResult(0, 0);

            final String[] numbers = item.split(",");
            if (numbers.length < 1 || numbers.length > 30) {
                throw new RuntimeException("Number of numbers les than 1 or more than 30");
            }

            Stream.of(numbers).mapToInt(n -> Integer.parseInt(n)).forEach((value) -> {
                processResult.setNumberOfValues(processResult.getNumberOfValues() + 1);
                processResult.setSumOfValues(processResult.getSumOfValues() + value);
            });

            try {
                return objectMapper.writeValueAsString(processResult);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("ProcessResult mapping exception");
            }

        });
    }

}


package awin;

public class ProcessResult {
    private Integer numberOfValues;
    private Integer sumOfValues;

    public ProcessResult() { }

    public ProcessResult(Integer sumOfValues, Integer numberOfValues) {
        this.numberOfValues = numberOfValues;
        this.sumOfValues = sumOfValues;
    }

    public Integer getNumberOfValues() {
        return numberOfValues;
    }

    public void setNumberOfValues(Integer numberOfValues) {
        this.numberOfValues = numberOfValues;
    }

    public Integer getSumOfValues() {
        return sumOfValues;
    }

    public void setSumOfValues(Integer sumOfValues) {
        this.sumOfValues = sumOfValues;
    }
}
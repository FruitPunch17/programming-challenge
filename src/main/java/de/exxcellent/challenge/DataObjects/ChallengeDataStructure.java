package de.exxcellent.challenge.DataObjects;

import de.exxcellent.challenge.Exceptions.WrongNameException;

import java.util.List;

/**
 * This class holds the data for the challenge in an appropriate structure.
 */
public class ChallengeDataStructure {
    /**
     * This constructs a ChallengeDataStructure. The indices are the objects to be compared for the challenge (e.g. Days, Teams,...).
     * The values are data obtained from the indices (e.g. Temperature, Goals scored,...). There is only one group of indices
     * but there may be multiple values for each single index.
     * @param indexHeader The name of the group in which the indices are aggregated.
     * @param indices The list of indices.
     * @param valueHeader A list of the names of the values (e.g. MaxTemp, MinTemp, AirPressure,...).
     * @param values The values. These should be ordered such that the i-th entry, which is a list, corresponds to the i-th index
     *               and the j-th entry of that list corresponds to the j-th entry in the value header.
     */
    public ChallengeDataStructure(String indexHeader, List<String> indices, List<String> valueHeader, List<List<String>> values){
        this.valueHeader = valueHeader;
        this.indexHeader = indexHeader;
        this.indices = indices;

        try{
            this.values = values.stream().map(row -> row.stream().map(Double::parseDouble).toList()).toList();
        } catch(NumberFormatException e){
            throw new NumberFormatException("A field in the given values cannot be converted to an integer. Please check the values.");
        }
    }

    private final String indexHeader;
    public String getIndexHeader(){
        return indexHeader;
    }

    private final List<String> indices;
    public List<String> getIndices(){
        return indices;
    }

    private final List<String> valueHeader;
    public List<String> getValueHeader() {
        return valueHeader;
    }

    private final List<List<Double>> values;
    public List<List<Double>> getValues() {
        return values;
    }

    /**
     * This returns all values that belong to a specific valueHeader.
     * @param columnName This should be a name that is contained in the list valueHeaders.
     * @return all values that belong to a specific valueHeader
     * @throws WrongNameException If the columnName is not contained in the valueHeaders, this exception is thrown.
     */
    public List<Double> getValueColumnByName(String columnName) throws WrongNameException {
        int columnPosition = getValueHeader().indexOf(columnName);
        if(columnPosition == -1) throw new WrongNameException("The name of the column could not be found.");
        List<Double> column = getValues().stream().map(row -> row.get(columnPosition)).toList();
        return column;
    }
}

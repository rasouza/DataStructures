import java.util.Comparator;

public class CyclicalComparator implements Comparator<String> {
	int i;
	
	public String shiftCyclically(String word, int pos) {
		if (pos > word.length()) pos = 0;
    	return word.substring(word.length()-pos, word.length()) + word.substring(0, word.length()-pos);
    }
	
	@Override
	public int compare(final String lhs,String rhs) {
		rhs = shiftCyclically(rhs, i);
		String newStr = shiftCyclically(lhs, i);
		return newStr.compareTo(rhs);
	}
	
	public CyclicalComparator(int pos) {
		i = pos;
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.*;


class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String[] lines = {"The one 2 Pick is the last one!!", "I 'don't know'' I dont know I owe $5.75", "s; dP5 389m    j7zs;45 bz0 s.x4Dp"};
        for (String str : lines) {
            System.out.printf("\"%s\"\nUnique words: %d\n\n", str, uniqueWords(Arrays.asList(str)));
        }

        String file = "TimeMachine.txt";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = reader.lines().collect(Collectors.toList());
        reader.close();

        System.out.println(file + "\nUnique words: " + uniqueWords(list));
    }

    public static int uniqueWords(List<String> lines) {
        int count = 0;

		for (int i = 0; i < lines.size(); i++) {
			count += getUniqueWords(lines.get(i));
		}

        return count;    
    }

    public static int getUniqueWords(String numRemovedStr) {
		// make a set to store items
        Set<String> newSet = new HashSet<>();

        // clean data
        // remove numbers
        for (int i = 0; i < numRemovedStr.length(); i++) {
            if (numRemovedStr.substring(i, i+1).matches(".*\\d.*")) {
                numRemovedStr = numRemovedStr.substring(0, i) + " " + numRemovedStr.substring(i+1);
                i--;
            }
        }

        // split array to have multiple items
		ArrayList<String> numRemovedArr = new ArrayList<String>();
        numRemovedArr.addAll(Arrays.asList(numRemovedStr.split("\\s+")));

		// convert all items to lowercase
		numRemovedArr.replaceAll(String::toLowerCase);

		// remove punctuation
		for (String item : numRemovedArr) {
			for (int i = 0; i < item.length(); i++) {
				if (item.substring(i, i+1).matches("(.*)[\\p{P}](.*)") && i == 0) {
					item = item.substring(0, i) + item.substring(i+1);
					i--;
				}
				else if (item.substring(i, i+1).matches("(.*)[\\p{P}](.*)") && i == item.length()-1) {
					item = item.substring(0, i);
					i--;
					if (item.substring(i, i+1).matches("(.*)[\\p{P}](.*)") && i == item.length()-1) {
						i--;
					}	
				}
			}
			// add item to newSet
			if (!newSet.contains(item) && item.matches(".*[a-zA-Z]+.*")) {
				newSet.add(item);
			}
		}

		return newSet.size();
    }

}

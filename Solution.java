import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new FileInputStream("codons_testcases/input01.txt"));
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		scanner.nextLine(); //Skip Codon Comment
		ArrayList<String> inputStrings = new ArrayList<String>();
		String s = "";
		while(scanner.hasNextLine()){
			s = scanner.nextLine();
			inputStrings.add(s);
		}
		String DNA = inputStrings.get(inputStrings.size()-1);
		HashMap<String,String> map = createHashMap(inputStrings);
		solve(map,DNA);
	}
	
	public static HashMap<String,String> createHashMap(ArrayList<String> input){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("ATG", "M");// Add Start Codon
		for(int i = 0; i < input.size() - 1; i++){
			String s = input.get(i);
			map.put(s.substring(0,3), s.substring(4,5));
		}
		return map;
	}
	
	public static void solve(HashMap<String,String> map, String DNA){
		ArrayList<String> codons = new ArrayList<String>();

		for(int i = 0; i < DNA.length() - 3; i++){
			if(map.containsKey(DNA.substring(i,i+3))){
					codons.add(DNA.substring(i,i+3));
			}
		}
		
		ArrayList<Integer> starts = new ArrayList<Integer>();
		ArrayList<Integer> stops = new ArrayList<Integer>();
		
		for(int i = 0; i < codons.size(); i++){
			if(codons.get(i).equalsIgnoreCase("ATG")){
				starts.add(i);
			}
		}
		
		for(int i = starts.get(0); i < DNA.length()-3;i = i+3){
			if(isStopCodon(DNA.substring(i,i+3))){
				stops.add(i);
			}
		}

		int previousFinish = 0;
		for(int i = 0; i < starts.size(); i++){
			int startPoint = starts.get(i);
			if(startPoint < previousFinish) continue;
			for(int j = startPoint; j < DNA.length(); j = j+3){
				if(isStopCodon(DNA.substring(j,j+3))) break;
				System.out.print(map.get(DNA.substring(j,j+3)));
				previousFinish = j;
			}
			System.out.println();
		}

	}
	
	public static Boolean isStopCodon(String codon){
		
		if(codon.equalsIgnoreCase("TAA")){
			return true;
		}else if (codon.equalsIgnoreCase("TAG")){
			return true;
		}else if (codon.equalsIgnoreCase("TGA")){
			return true;
		}else
			return false;
	}

}

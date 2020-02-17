/*Part 1: Finding many Genes
This assignment is to write the code from the lesson to make the following improvements to your algorithm:

A. Find a gene in a strand of DNA where the stop codon could be any of the three stop codons “TAA”, “TAG”, or “TGA”.

B. Find all the genes (where the stop codon could be any of the three stop codons) in a strand of DNA.

This will help you see if you really understood how to put the code together, and might identify a part that you did not fully understand. If you get stuck, then you can go back and watch the coding videos that go with this lesson again.

Specifically, you should do the following:

1. Create a new Java project named StringsSecondAssignments. You can put all the classes for this programming exercise in this project.

2. Create a new Java Class named Part1. The following methods go in this class.

3. Write the method findStopCodon that has three parameters, a String parameter named dna, an integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna, and a String parameter named stopCodon. This method returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex. If there is no such stopCodon, this method returns the length of the dna strand.

4. Write the void method testFindStopCodon that calls the method findStopCodon with several examples and prints out the results to check if findStopCodon is working correctly. Think about what types of examples you should check. For example, you may want to check some strings of DNA that have genes and some that do not. What other examples should you check?

5. Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:

Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
6. Write the void method testFindGene that has no parameters. You should create five DNA strings. The strings should have specific test cases such as DNA with no “ATG”, DNA with “ATG” and one valid stop codon, DNA with “ATG” and multiple valid stop codons, DNA with “ATG” and no valid stop codons. Think carefully about what would be good examples to test. For each DNA string you should:

Print the DNA string.
Calculate the gene by sending this DNA string as an argument to findGene. If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
7. Write the void method printAllGenes that has one String parameter dna, representing a string of DNA. In this method you should repeatedly find genes and print each one until there are no more genes. Hint: remember you learned a while(true) loop and break.*/

package Week2.StringSecondAssignment;

import java.util.List;
//import java.util.Random;
import java.util.*;
class Part1 {
  int findStopCodon(String dna, int startIdx, String stopCodon) {
    int idx = dna.indexOf(stopCodon, startIdx);
    return idx == -1 || (idx - startIdx) % 3 != 0 ? dna.length() : idx;
  }
  void testFindStopCodon() {
    List<String> tests = Arrays.asList(
            "ATGTAA",
            "ATGGTATAA",
            "AAATGCCCTAACTAGATTAAGAAACC",
            "AGTCAA",
            "ATGAGCCGTAATCGAC",
            "ATGGCTCCATAA",
            "ATGCCCCTACGTAATCTA",
            "AGTGATTCGGCTCTGTAA",
            "AG",
            "TAA",
            "AGT"
    );
    //Random gen = new Random();
    for (String test : tests) {
      // int idx = gen.nextInt(test.length());
      int idx = test.indexOf("ATG");
      System.out.println(test + " stop codon index starting from the index " + idx + " is at " + findStopCodon(test, idx, "TAA"));
    }
  }
  String findGene(String dna) {
    int idx = dna.indexOf("ATG");
    //System.out.println(idx);
    if (idx == -1)
      return "";
    String ans = "";
    int ends[] = new int[3];
    ends[0] = findStopCodon(dna, idx + 3, "TAA");
    ends[1] = findStopCodon(dna, idx + 3, "TAG");
    ends[2] = findStopCodon(dna, idx + 3, "TGA");
    //System.out.println(Arrays.toString(ends));
    int minDist = Integer.MAX_VALUE;
    for (int end : ends) {
      if (end != dna.length() && minDist > (end - idx)) {

        minDist = end - idx;
        ans = dna.substring(idx, end + 3);

      }
    }
    return ans;
  }
  void testFindGene() {
    List<String> tests = Arrays.asList(
            "ATGTAA",
            "ATGGTATAG",
            "AAATGCCCTGACTAGATTAAGAAACC",
            "AGTCAA",
            "AGTCCGTAGATCGAC",
            "AATGCTAACTAGCTGACTAAT"
    );

    for (String test : tests) {
      System.out.println("Test " + test + " : " + findGene(test));
    }
  }

  void printGene(String dna, int startIdx, int stopIdx) {
    if (stopIdx != dna.length())
      System.out.println("Gene found is: " + dna.substring(startIdx, stopIdx + 3));
  }

  void printAllGenes(String dna) {
    int startIdx = dna.indexOf("ATG");

    while (startIdx != -1) {
      printGene(dna, startIdx, findStopCodon(dna, startIdx, "TAA"));
      printGene(dna, startIdx, findStopCodon(dna, startIdx, "TGA"));
      printGene(dna, startIdx, findStopCodon(dna, startIdx, "TAG"));
      startIdx = dna.indexOf("ATG", startIdx + 3);
    }
  }
  public static void main(String[] args) {
    Part1 object = new Part1();
    //System.out.println("Test for find stop codon ... ");
    // object.testFindStopCodon();
    // System.out.println("\nTest for find gene ... ");
    //  object.testFindGene();
    //  System.out.println("\nTest for finding multiple genes: ");
    object.printAllGenes("ATGTAATAGACGGAGTCGTTGCTAGAGTTGA");
/*Test for finding multiple genes:
Gene found is: ATGTAA
Gene found is: ATGTAATAG*/
  }
}

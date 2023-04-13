package designandAnalysisHW0;

import java.util.Scanner;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class Assignmenttwo {
	//this method takes two parameters and checks if the given string matches with any
		//of the hash values in the array using MD5 Hash function
		
		public static void systemVerify(String[] hash_Array, String str) {
			
			//prints the concatinated value and the corresponding MD5Hash value
			
			System.out.println("Password||salt is " +str);
			
			String newMD5 = computeMD5hash(str);
			
			System.out.println("MD5 hash value is " + newMD5);
			
			//sets boolean variable 'match' to false 
			
			boolean match = false;
			
			for(int i =0; i<hash_Array.length; i++) {
				
				//if the value matches it prints match found message
				
				if(newMD5.equals(hash_Array[i])) {
					
					System.out.println("The input password and salt matches the provided hash value in the database");
					
					match = true;
				}
			}
			
			//if the match is not found then it prints the match is not found message
			
			if(!match) {
				
				System.out.println("The input password and salt does not match the provided hash value in the database");
				
			}
			
			
		}
		//this method contains three parameters, attempts to crack the password and salt for the giver UID
		
		//by trying all possible combinations of password and salt in the ranges [0000,1000] and [000,100]
		
		public static void bruteforceCracker(String userID, String hash, String[] hash_Array) {
			
			 //variables used to store the current password being tried 
			
			String password;
			
			 String salt;
			 
			 String calc_MD5;
			 
			 //the loop iterates through all possible passwords from 0000 to 1000
			 
			 for(int i = 0; i<1001; i++) {
				 
				 password=String.valueOf(i);
				 
				 //checks the length of the password string 
				 
				 //if its less than 4, then it adds 0 to the string
				 
				 while(password.length()<4) {
					 
					 password="0" + password;
					 
				 }
				 //this loop runs from 0 to 101 iteration
				 
				 for(int j =0; j<101; j++) {
					 
					 salt=String.valueOf(j);
					 
					 //make sures that 'salt' is alaways a 3 digit string 
					 
					 while(salt.length()<3) {
						 
						 salt="0"+ salt;
						 
					 }
					 
					 //this string concatinates password and salt 
					 
					 String concat = password + salt;
					 
					 //MD5 hash of 'concat' stores the result in the variable'calc_MD5'
					 
					 calc_MD5 = computeMD5hash(concat);
					 
					 //if equal then the correct password and salt have been found
					 
					 if(calc_MD5.equals(hash)) {
						 
						 System.out.println("The password is : " + password + " salt: " + salt);
						 
						//calls systemVerify to verify the hash value of the password and salt value
						
						 systemVerify(hash_Array, concat);
						
						 return;
						 
					 }
				 }
			 }
			 
			 //if the correct password and salt are not found in the loop, then the following gets printed
			 
			 System.out.println("There is no password and salt matches found");
		}
		
		//method to compute the MD5 hash of a string 
		
		public static  String computeMD5hash(String input) { //throws NoSuchAlgorithmException{
			try {
				//MD5 hash is calculated for the input string using the digest method
				//to return the byte array
				
				MessageDigest md = MessageDigest.getInstance("MD5");
				
						byte [] messageDigest = md.digest(input.getBytes());
						
						//BigInteger is created from byte array and converted to hexadecimal using 'toString'
						
						BigInteger no = new BigInteger(1, messageDigest);
						
						String hashtext = no.toString(16);
						//if length is less than 32 characters then the 0's are added
						
						while(hashtext.length() < 32) {
							
							hashtext = "0" +hashtext;
						}
						return hashtext;
						
						
			}
			catch(NoSuchAlgorithmException e) {
			
				throw new RuntimeException(e);
			}
		}
		//reads the content of a text file and return s it as a String array
		public static String[] getText(String FilePath) throws IOException{
			
			List<String> listOfStrings = new ArrayList<String>();
			
			//reads the file line by line and adds each line to 'listOfStrings'
			
			BufferedReader buff = new BufferedReader(new FileReader(FilePath));
			
			String line = buff.readLine();
			
			while(line != null) {
			
				listOfStrings.add(line);
				
				line = buff.readLine();
				
			}
			
			buff.close();
			//list of strings is convereted to string array and returned 
			
			String[] array = listOfStrings.toArray(new String[0]);
			
			return array;
		}

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
			
			String uidFilePath = "UID.txt"; //reads the files from UID.txt
			
			String passwordFilePath = "Password.txt"; //reads files from password.txt
			
			String saltFilePath = "Salt.txt";//reads files from salt.txt file
			
			String hashFilePath = "Hash.txt"; //reads file from hash.txt
			
			// TODO Auto-generated method stub
			
			//the get function reads the contents of each file 
			
			String [] userID_Array = getText(uidFilePath);
			String [] password_Array = getText(passwordFilePath);
			String [] salt_Array = getText(saltFilePath);
			String [] hash_Array = getText(hashFilePath);
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select the user ID from 1-100. Enter UID: ");
			int userID = scanner.nextInt();
			//subtracts 1 from the input to match the index value of the arrays
			userID = userID-1;
			System.out.println("Password value: " + password_Array[userID]);
			System.out.println("Salt value: " + salt_Array[userID]);
			String str = password_Array[userID] + salt_Array[userID]; systemVerify(hash_Array, str);
			
			System.out.println("Pick a number from 1-100, to crack the password and salt value");
			userID = scanner.nextInt();
	userID = userID-1;
			scanner.close();
			System.out.println("UID: " + userID_Array[userID] );
			
			bruteforceCracker(userID_Array[userID], hash_Array[userID], hash_Array);
			
		}

}

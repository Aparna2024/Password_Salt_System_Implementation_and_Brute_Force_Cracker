# Password_Salt_System_Implementation_and_Brute_Force_Cracker

In this repository you will find a code that is used to implement a password salt verification system. A salt is stored in the database and added to the hashing process to force the uniqueness of the password, which is easy to verify and can increase the complexity without increasing user requriments.
The salt does not need to be kept secret and the extra security which comes in with the salt is that it even maps same passwords to different hashes depending on the salt.

Task is using the UID.txt and Hash.txt files, implement the verification system, such that the given example of the password and salt can match with the hash value in the Hash.txt file.

For example, in your UID.txt file, the first UID is 001, and in your Password.txt file, the password is 0599, and in your Salt.txt file, the salt associated with the first UID is 054. When applying the MD5 Hash Function with the encode format as ‘utf- 8’ as shown in the figure, the expected output should be 4a1d6f102cd95fac33853e4d72fe1dc5 (See the first line of your hash.txt file). It is worth to mention that, the concatenation between password and salt needs to be in the format of (password||salt). For example, with the aforementioned input, the concatenation result will be 0599054. Note that, 0 should not be omitted

Requirements for the Design of Verification System:
def computeMD5hash(my_string): m = hashlib.md5() m.update(my_string.encode('utf-8')) return m.hexdigest()
A) The designed verification system should be able to correctly verify the example shown above. When the input is correct, the system will output a String “The input password and salt matches the hash value in the database”. Otherwise, the output should be “The input password and salt does not match the hash value in the database”

Implementation of Brute Force Cracker
To reduce the complexity for cracking the password and salt, the passwords are randomly set in the range of [0000, 1000], while the salt is randomly set in the range of [000,100] for each UID. One easy idea to implement a cracker system is to brute-forcely try all possible combinations of password and salt for one UID. As the Hash.txt and UID.txt files are given, students are requested to implement a cracker system which could find the correct password and salt for a specific UID.

Requirements for the Design of Cracker System :

A) For a specific UID, the cracker system can output the correct password and salt value. For example, when input the UID as 001, the output should be “password: 0599; salt: 054”


# CipherDash

Task:  (the original as seen below , was changed once I got bored and started playing with it)

• Encode the 1st character of the message with its corresponding integer value (from ASCII code)

• Encode the following characters: (a) by adding the ASCII integer value of each of them to the code of its predecessor, (b) taking the remainder of dividing this sum by a constant

• The constant is called the encryption key and is (supposedly) secret

• We assume that messages end with the character #

• Write a java program that implements the encryption algorithm so that the resulting encoded message is a sequence of integers ending with -1

• Write also the decryption algorithm that takes as input a sequence of integers ending with -1 and computes the original message

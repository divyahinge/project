#include<stdio.h>
int main()
{

	char ch='a';
	printf("\nEnter the character:");
	scanf("%c",&ch);

	if(ch=='a'||ch=='e'||ch=='o'||ch=='i'||ch=='u'||ch=='A')
	printf("\nIt is vowel");
	else
	printf("\nIt is not vowel");


	return 0;

}
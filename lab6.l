%option NOYYWRAP

num [0-9]*
keyword "int"|"if"|"else"|"return"
octal 0[0-7]*
hexadecimal 0X[a-f0-9]*|0X[a-f0-9]*

real [0-9]&\.[[0-9]+\.[0-9]*
Relop "<"|"<="|">"|">="|"=="|"!="
Assignop "="|"+="|"-="
Leftpar "("|"["|"{"
Rightpar ")"|"]"|"}"
Delimeter ";"|","
id [A-Za-z_]*[A-Za-z]

%%
[\t\n];

{keyword} {printf("Token keyword:Lexeme: %s \n",yytext);return 1;}
{id} {printf("Token identifier:Lexeme: %s \n",yytext);return 1;}
{num} {prinft("Token number:Lexeme: %s \n",yytext);return 1;}
{real} {printf("Token number:lexeme: %s \n",yytext);return 1;}
{Relop} {printf("Token number:lexeme: %s \n",yytext);return 1;}
{Assignop} {printf("Token number:lexeme: %s \n",yytext);return 1;}
{Leftpar} {prinft("Token number:lexeme: %s \n",yytext);return 1;}
{Rightpar} {printf("Token number:lexeme: %s \n",yytext);return 1;}
{leftpar} {printf("Token number:lexeme: %s \n",yytext);return 1;}

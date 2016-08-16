/* A Bison parser, made by GNU Bison 3.0.2.  */

/* Skeleton implementation for Bison LALR(1) parsers in Java

   Copyright (C) 2007-2013 Free Software Foundation, Inc.

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

package Parser;
/* First part of user declarations.  */

/* "Parser.java":37  */ /* lalr1.java:91  */

/* "Parser.java":39  */ /* lalr1.java:92  */
/* "%code imports" blocks.  */
/* "agu.y":12  */ /* lalr1.java:93  */

    import java.io.*;
    import java.util.List;
    import java.util.ArrayList;
    import Tree.*;
    import Lexer.Token;
 

/* "Parser.java":50  */ /* lalr1.java:93  */

/**
 * A Bison parser, automatically generated from <tt>agu.y</tt>.
 *
 * @author LALR (1) parser skeleton written by Paolo Bonzini.
 */
@SuppressWarnings("unchecked") public class Parser
{
    /** Version number for the Bison executable that generated this parser.  */
  public static final String bisonVersion = "3.0.2";

  /** Name of the skeleton that generated this parser.  */
  public static final String bisonSkeleton = "lalr1.java";


  /**
   * True if verbose error messages are enabled.
   */
  private boolean yyErrorVerbose = true;

  /**
   * Return whether verbose error messages are enabled.
   */
  public final boolean getErrorVerbose() { return yyErrorVerbose; }

  /**
   * Set the verbosity of error messages.
   * @param verbose True to request verbose error messages.
   */
  public final void setErrorVerbose(boolean verbose)
  { yyErrorVerbose = verbose; }



  /**
   * A class defining a pair of positions.  Positions, defined by the
   * <code>Position</code> class, denote a point in the input.
   * Locations represent a part of the input through the beginning
   * and ending positions.
   */
  public class Location {
    /**
     * The first, inclusive, position in the range.
     */
    public Position begin;

    /**
     * The first position beyond the range.
     */
    public Position end;

    /**
     * Create a <code>Location</code> denoting an empty range located at
     * a given point.
     * @param loc The position at which the range is anchored.
     */
    public Location (Position loc) {
      this.begin = this.end = loc;
    }

    /**
     * Create a <code>Location</code> from the endpoints of the range.
     * @param begin The first position included in the range.
     * @param end   The first position beyond the range.
     */
    public Location (Position begin, Position end) {
      this.begin = begin;
      this.end = end;
    }

    /**
     * Print a representation of the location.  For this to be correct,
     * <code>Position</code> should override the <code>equals</code>
     * method.
     */
    public String toString () {
      if (begin.equals (end))
        return begin.toString ();
      else
        return begin.toString () + "-" + end.toString ();
    }
  }



  
  private Location yylloc (YYStack rhs, int n)
  {
    if (n > 0)
      return new Location (rhs.locationAt (n-1).begin, rhs.locationAt (0).end);
    else
      return new Location (rhs.locationAt (0).end);
  }

  /**
   * Communication interface between the scanner and the Bison-generated
   * parser <tt>Parser</tt>.
   */
  public interface Lexer {
    /** Token returned by the scanner to signal the end of its input.  */
    public static final int EOF = 0;

/* Tokens.  */
    /** Token number,to be returned by the scanner.  */
    static final int ALIAS = 258;
    /** Token number,to be returned by the scanner.  */
    static final int MUT = 259;
    /** Token number,to be returned by the scanner.  */
    static final int CLASS = 260;
    /** Token number,to be returned by the scanner.  */
    static final int SET = 261;
    /** Token number,to be returned by the scanner.  */
    static final int VAL = 262;
    /** Token number,to be returned by the scanner.  */
    static final int VAR = 263;
    /** Token number,to be returned by the scanner.  */
    static final int LET = 264;
    /** Token number,to be returned by the scanner.  */
    static final int DEF = 265;
    /** Token number,to be returned by the scanner.  */
    static final int IF = 266;
    /** Token number,to be returned by the scanner.  */
    static final int THEN = 267;
    /** Token number,to be returned by the scanner.  */
    static final int ELSE = 268;
    /** Token number,to be returned by the scanner.  */
    static final int WHEN = 269;
    /** Token number,to be returned by the scanner.  */
    static final int WHILE = 270;
    /** Token number,to be returned by the scanner.  */
    static final int DO = 271;
    /** Token number,to be returned by the scanner.  */
    static final int NOT = 272;
    /** Token number,to be returned by the scanner.  */
    static final int IN = 273;
    /** Token number,to be returned by the scanner.  */
    static final int IDENTIFIER = 274;
    /** Token number,to be returned by the scanner.  */
    static final int T_IDENTIFIER = 275;
    /** Token number,to be returned by the scanner.  */
    static final int INT_LITERAL = 276;
    /** Token number,to be returned by the scanner.  */
    static final int CHAR_LITERAL = 277;
    /** Token number,to be returned by the scanner.  */
    static final int PLUS = 278;
    /** Token number,to be returned by the scanner.  */
    static final int SUBTRACT = 279;
    /** Token number,to be returned by the scanner.  */
    static final int TIMES = 280;
    /** Token number,to be returned by the scanner.  */
    static final int FSLASH = 281;
    /** Token number,to be returned by the scanner.  */
    static final int OP = 282;
    /** Token number,to be returned by the scanner.  */
    static final int CP = 283;
    /** Token number,to be returned by the scanner.  */
    static final int SEMICOLON = 284;
    /** Token number,to be returned by the scanner.  */
    static final int OCURLYBRACE = 285;
    /** Token number,to be returned by the scanner.  */
    static final int CCURLYBRACE = 286;
    /** Token number,to be returned by the scanner.  */
    static final int COMMA = 287;
    /** Token number,to be returned by the scanner.  */
    static final int LARROW = 288;
    /** Token number,to be returned by the scanner.  */
    static final int DOLLAR = 289;
    /** Token number,to be returned by the scanner.  */
    static final int COLON = 290;
    /** Token number,to be returned by the scanner.  */
    static final int RARROW = 291;
    /** Token number,to be returned by the scanner.  */
    static final int EQEQ = 292;
    /** Token number,to be returned by the scanner.  */
    static final int PERCENT = 293;
    /** Token number,to be returned by the scanner.  */
    static final int ANDAND = 294;
    /** Token number,to be returned by the scanner.  */
    static final int OROR = 295;
    /** Token number,to be returned by the scanner.  */
    static final int EQ = 296;
    /** Token number,to be returned by the scanner.  */
    static final int NEQ = 297;
    /** Token number,to be returned by the scanner.  */
    static final int LESS = 298;
    /** Token number,to be returned by the scanner.  */
    static final int MORE = 299;
    /** Token number,to be returned by the scanner.  */
    static final int LESST = 300;
    /** Token number,to be returned by the scanner.  */
    static final int MORET = 301;
    /** Token number,to be returned by the scanner.  */
    static final int SEPARATOR = 302;
    /** Token number,to be returned by the scanner.  */
    static final int STRING_LITERAL = 303;
    /** Token number,to be returned by the scanner.  */
    static final int ERROR = 304;
    /** Token number,to be returned by the scanner.  */
    static final int BOOL_LITERAL = 305;


    /**
     * Method to retrieve the beginning position of the last scanned token.
     * @return the position at which the last scanned token starts.
     */
    Position getStartPos ();

    /**
     * Method to retrieve the ending position of the last scanned token.
     * @return the first position beyond the last scanned token.
     */
    Position getEndPos ();

    /**
     * Method to retrieve the semantic value of the last scanned token.
     * @return the semantic value of the last scanned token.
     */
    Object getLVal ();

    /**
     * Entry point for the scanner.  Returns the token identifier corresponding
     * to the next token and prepares to return the semantic value
     * and beginning/ending positions of the token.
     * @return the token identifier corresponding to the next token.
     */
    int yylex () throws java.io.IOException;

    /**
     * Entry point for error reporting.  Emits an error
     * referring to the given location in a user-defined way.
     *
     * @param loc The location of the element to which the
     *                error message is related
     * @param msg The string for the error message.
     */
     void yyerror (Location loc, String msg);
  }

  /**
   * The object doing lexical analysis for us.
   */
  private Lexer yylexer;
  
  



  /**
   * Instantiates the Bison-generated parser.
   * @param yylexer The scanner that will supply tokens to the parser.
   */
  public Parser (Lexer yylexer) 
  {
    
    this.yylexer = yylexer;
    
  }

  private java.io.PrintStream yyDebugStream = System.err;

  /**
   * Return the <tt>PrintStream</tt> on which the debugging output is
   * printed.
   */
  public final java.io.PrintStream getDebugStream () { return yyDebugStream; }

  /**
   * Set the <tt>PrintStream</tt> on which the debug output is printed.
   * @param s The stream that is used for debugging output.
   */
  public final void setDebugStream(java.io.PrintStream s) { yyDebugStream = s; }

  private int yydebug = 0;

  /**
   * Answer the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   */
  public final int getDebugLevel() { return yydebug; }

  /**
   * Set the verbosity of the debugging output; 0 means that all kinds of
   * output from the parser are suppressed.
   * @param level The verbosity level for debugging output.
   */
  public final void setDebugLevel(int level) { yydebug = level; }

  /**
   * Print an error message via the lexer.
   * Use a <code>null</code> location.
   * @param msg The error message.
   */
  public final void yyerror (String msg)
  {
    yylexer.yyerror ((Location)null, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param loc The location associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Location loc, String msg)
  {
    yylexer.yyerror (loc, msg);
  }

  /**
   * Print an error message via the lexer.
   * @param pos The position associated with the message.
   * @param msg The error message.
   */
  public final void yyerror (Position pos, String msg)
  {
    yylexer.yyerror (new Location (pos), msg);
  }

  protected final void yycdebug (String s) {
    if (yydebug > 0)
      yyDebugStream.println (s);
  }

  private final class YYStack {
    private int[] stateStack = new int[16];
    private Location[] locStack = new Location[16];
    private Object[] valueStack = new Object[16];

    public int size = 16;
    public int height = -1;

    public final void push (int state, Object value                            , Location loc) {
      height++;
      if (size == height)
        {
          int[] newStateStack = new int[size * 2];
          System.arraycopy (stateStack, 0, newStateStack, 0, height);
          stateStack = newStateStack;
          
          Location[] newLocStack = new Location[size * 2];
          System.arraycopy (locStack, 0, newLocStack, 0, height);
          locStack = newLocStack;

          Object[] newValueStack = new Object[size * 2];
          System.arraycopy (valueStack, 0, newValueStack, 0, height);
          valueStack = newValueStack;

          size *= 2;
        }

      stateStack[height] = state;
      locStack[height] = loc;
      valueStack[height] = value;
    }

    public final void pop () {
      pop (1);
    }

    public final void pop (int num) {
      // Avoid memory leaks... garbage collection is a white lie!
      if (num > 0) {
        java.util.Arrays.fill (valueStack, height - num + 1, height + 1, null);
        java.util.Arrays.fill (locStack, height - num + 1, height + 1, null);
      }
      height -= num;
    }

    public final int stateAt (int i) {
      return stateStack[height - i];
    }

    public final Location locationAt (int i) {
      return locStack[height - i];
    }

    public final Object valueAt (int i) {
      return valueStack[height - i];
    }

    // Print the state stack on the debug stream.
    public void print (java.io.PrintStream out)
    {
      out.print ("Stack now");

      for (int i = 0; i <= height; i++)
        {
          out.print (' ');
          out.print (stateStack[i]);
        }
      out.println ();
    }
  }

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return success (<tt>true</tt>).
   */
  public static final int YYACCEPT = 0;

  /**
   * Returned by a Bison action in order to stop the parsing process and
   * return failure (<tt>false</tt>).
   */
  public static final int YYABORT = 1;



  /**
   * Returned by a Bison action in order to start error recovery without
   * printing an error message.
   */
  public static final int YYERROR = 2;

  /**
   * Internal return codes that are not supported for user semantic
   * actions.
   */
  private static final int YYERRLAB = 3;
  private static final int YYNEWSTATE = 4;
  private static final int YYDEFAULT = 5;
  private static final int YYREDUCE = 6;
  private static final int YYERRLAB1 = 7;
  private static final int YYRETURN = 8;


  private int yyerrstatus_ = 0;


  /**
   * Return whether error recovery is being done.  In this state, the parser
   * reads token until it reaches a known state, and then restarts normal
   * operation.
   */
  public final boolean recovering ()
  {
    return yyerrstatus_ == 0;
  }

  /** Compute post-reduction state.
   * @param yystate   the current state
   * @param yysym     the nonterminal to push on the stack
   */
  private int yy_lr_goto_state_ (int yystate, int yysym)
  {
    int yyr = yypgoto_[yysym - yyntokens_] + yystate;
    if (0 <= yyr && yyr <= yylast_ && yycheck_[yyr] == yystate)
      return yytable_[yyr];
    else
      return yydefgoto_[yysym - yyntokens_];
  }

  private int yyaction (int yyn, YYStack yystack, int yylen) 
  {
    Object yyval;
    Location yyloc = yylloc (yystack, yylen);

    /* If YYLEN is nonzero, implement the default value of the action:
       '$$ = $1'.  Otherwise, use the top of the stack.

       Otherwise, the following line sets YYVAL to garbage.
       This behavior is undocumented and Bison
       users should not rely upon it.  */
    if (yylen > 0)
      yyval = yystack.valueAt (yylen - 1);
    else
      yyval = yystack.valueAt (0);

    yy_reduce_print (yyn, yystack);

    switch (yyn)
      {
          case 2:
  if (yyn == 2)
    /* "agu.y":106  */ /* lalr1.java:489  */
    {
    /* se asigna el árbol al atributo tree */
    tree = new Program (((ArrayList<ASTree> )(yystack.valueAt (1-(1)))), (yyloc).begin);
    return YYACCEPT;
};
  break;
    

  case 3:
  if (yyn == 3)
    /* "agu.y":114  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<ASTree> ();

 };
  break;
    

  case 4:
  if (yyn == 4)
    /* "agu.y":119  */ /* lalr1.java:489  */
    {
    ((ArrayList<ASTree> )(yystack.valueAt (3-(1)))).add (((ASTree)(yystack.valueAt (3-(2)))));
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 6:
  if (yyn == 6)
    /* "agu.y":128  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 7:
  if (yyn == 7)
    /* "agu.y":132  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 8:
  if (yyn == 8)
    /* "agu.y":136  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 9:
  if (yyn == 9)
    /* "agu.y":140  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((FuncDef)(yystack.valueAt (1-(1))));
};
  break;
    

  case 10:
  if (yyn == 10)
    /* "agu.y":144  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 11:
  if (yyn == 11)
    /* "agu.y":148  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 12:
  if (yyn == 12)
    /* "agu.y":152  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((StmtWhile)(yystack.valueAt (1-(1))));
};
  break;
    

  case 13:
  if (yyn == 13)
    /* "agu.y":156  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((ASTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 14:
  if (yyn == 14)
    /* "agu.y":162  */ /* lalr1.java:489  */
    {
    yyval = new TypeID(((String)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 15:
  if (yyn == 15)
    /* "agu.y":166  */ /* lalr1.java:489  */
    {
    yyval = new TypeRA(((TypeTree)(yystack.valueAt (3-(1)))),((TypeTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 16:
  if (yyn == 16)
    /* "agu.y":170  */ /* lalr1.java:489  */
    {
    yyval = new TypeP(((TypeTree)(yystack.valueAt (3-(2)))), (yyloc).begin);
};
  break;
    

  case 17:
  if (yyn == 17)
    /* "agu.y":176  */ /* lalr1.java:489  */
    {
    yyval = new AliasDecl(((String)(yystack.valueAt (4-(2)))),((TypeTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 18:
  if (yyn == 18)
    /* "agu.y":182  */ /* lalr1.java:489  */
    {
    yyval = new VarDecl(((String)(yystack.valueAt (4-(2)))),((TypeTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 19:
  if (yyn == 19)
    /* "agu.y":186  */ /* lalr1.java:489  */
    {
    yyval = new VarDecl(((String)(yystack.valueAt (6-(2)))),((TypeTree)(yystack.valueAt (6-(4)))),((ASTree)(yystack.valueAt (6-(6)))), (yyloc).begin);
};
  break;
    

  case 20:
  if (yyn == 20)
    /* "agu.y":192  */ /* lalr1.java:489  */
    {
    yyval = new ConstDecl(((String)(yystack.valueAt (6-(2)))),((TypeTree)(yystack.valueAt (6-(4)))),((ASTree)(yystack.valueAt (6-(6)))), (yyloc).begin);
};
  break;
    

  case 21:
  if (yyn == 21)
    /* "agu.y":198  */ /* lalr1.java:489  */
    {
    yyval = new Assignment(((ArrayList<String> )(yystack.valueAt (4-(2)))),((ASTree)(yystack.valueAt (4-(4)))),"", (yyloc).begin);
};
  break;
    

  case 22:
  if (yyn == 22)
    /* "agu.y":202  */ /* lalr1.java:489  */
    {
    yyval = new Assignment(((ArrayList<String> )(yystack.valueAt (5-(3)))),((ASTree)(yystack.valueAt (5-(5)))),"$", (yyloc).begin);
};
  break;
    

  case 23:
  if (yyn == 23)
    /* "agu.y":206  */ /* lalr1.java:489  */
    {
    yyval = new Assignment(((String)(yystack.valueAt (4-(2)))),((ASTree)(yystack.valueAt (4-(4)))),"$", (yyloc).begin);
};
  break;
    

  case 24:
  if (yyn == 24)
    /* "agu.y":212  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<String> ();
};
  break;
    

  case 25:
  if (yyn == 25)
    /* "agu.y":216  */ /* lalr1.java:489  */
    {
    ((ArrayList<String> )(yystack.valueAt (2-(1)))).add(((String)(yystack.valueAt (2-(2)))));
    yyval = ((ArrayList<String> )(yystack.valueAt (2-(1))));
};
  break;
    

  case 26:
  if (yyn == 26)
    /* "agu.y":223  */ /* lalr1.java:489  */
    {
    yyval = new ClassDef(((String)(yystack.valueAt (6-(1)))),((String)(yystack.valueAt (6-(3)))),((ConstrArgs)(yystack.valueAt (6-(4)))),((ClassBody)(yystack.valueAt (6-(6)))), (yyloc).begin);
};
  break;
    

  case 27:
  if (yyn == 27)
    /* "agu.y":229  */ /* lalr1.java:489  */
    {
    yyval = "";
};
  break;
    

  case 28:
  if (yyn == 28)
    /* "agu.y":233  */ /* lalr1.java:489  */
    {
    yyval = "Mut";
};
  break;
    

  case 29:
  if (yyn == 29)
    /* "agu.y":239  */ /* lalr1.java:489  */
    {
    yyval = null;
};
  break;
    

  case 33:
  if (yyn == 33)
    /* "agu.y":253  */ /* lalr1.java:489  */
    {
    yyval =  new ClassDefBody(((ArrayList<DeclTree> )(yystack.valueAt (3-(2)))), (yyloc).begin);
};
  break;
    

  case 34:
  if (yyn == 34)
    /* "agu.y":259  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<DeclTree> ();
};
  break;
    

  case 35:
  if (yyn == 35)
    /* "agu.y":263  */ /* lalr1.java:489  */
    {
    ((ArrayList<DeclTree> )(yystack.valueAt (3-(1)))).add(((DeclTree)(yystack.valueAt (3-(2)))));
    yyval = ((ArrayList<DeclTree> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 39:
  if (yyn == 39)
    /* "agu.y":278  */ /* lalr1.java:489  */
    {
    yyval = new ConstrArgs(((ArrayList<ConstrArgDec> )(yystack.valueAt (3-(2)))), (yyloc).begin);
};
  break;
    

  case 40:
  if (yyn == 40)
    /* "agu.y":284  */ /* lalr1.java:489  */
    {
    yyval = null;
};
  break;
    

  case 42:
  if (yyn == 42)
    /* "agu.y":292  */ /* lalr1.java:489  */
    {
    ArrayList<ConstrArgDec> aux = new ArrayList<ConstrArgDec> ();
    aux.add(((ConstrArgDec)(yystack.valueAt (1-(1)))));
    yyval = aux;
};
  break;
    

  case 43:
  if (yyn == 43)
    /* "agu.y":298  */ /* lalr1.java:489  */
    {
    ((ArrayList<ConstrArgDec> )(yystack.valueAt (3-(1)))).add(((ConstrArgDec)(yystack.valueAt (3-(3)))));
    yyval = ((ArrayList<ConstrArgDec> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 44:
  if (yyn == 44)
    /* "agu.y":305  */ /* lalr1.java:489  */
    {
    yyval = new ConstrArgDec(((String)(yystack.valueAt (4-(1)))),((String)(yystack.valueAt (4-(2)))),((TypeTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 45:
  if (yyn == 45)
    /* "agu.y":311  */ /* lalr1.java:489  */
    {
    yyval = "";
};
  break;
    

  case 46:
  if (yyn == 46)
    /* "agu.y":315  */ /* lalr1.java:489  */
    {
    yyval = "Var";
};
  break;
    

  case 47:
  if (yyn == 47)
    /* "agu.y":319  */ /* lalr1.java:489  */
    {
    yyval = "Val";
};
  break;
    

  case 48:
  if (yyn == 48)
    /* "agu.y":325  */ /* lalr1.java:489  */
    {
    yyval = new ClassCtor(((String)(yystack.valueAt (4-(1)))),((ArrayList<ASTree> )(yystack.valueAt (4-(3)))), (yyloc).begin);
};
  break;
    

  case 49:
  if (yyn == 49)
    /* "agu.y":331  */ /* lalr1.java:489  */
    {
    yyval = new FuncDef(((String)(yystack.valueAt (6-(2)))),((ArrayList<FuncArg> )(yystack.valueAt (6-(3)))),((TypeTree)(yystack.valueAt (6-(4)))),((ASTree)(yystack.valueAt (6-(6)))), (yyloc).begin);
};
  break;
    

  case 50:
  if (yyn == 50)
    /* "agu.y":337  */ /* lalr1.java:489  */
    {
    yyval = new Body(((ASTree)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 51:
  if (yyn == 51)
    /* "agu.y":341  */ /* lalr1.java:489  */
    {
    yyval = new Body(((ASTree)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 52:
  if (yyn == 52)
    /* "agu.y":347  */ /* lalr1.java:489  */
    {
    yyval = null;
};
  break;
    

  case 53:
  if (yyn == 53)
    /* "agu.y":351  */ /* lalr1.java:489  */
    {
    yyval = ((TypeTree)(yystack.valueAt (2-(2))));
};
  break;
    

  case 54:
  if (yyn == 54)
    /* "agu.y":357  */ /* lalr1.java:489  */
    {
    ArrayList<FuncArg> aux = new ArrayList<FuncArg>();
    aux.add(((FuncArg)(yystack.valueAt (1-(1)))));
    yyval = aux;
};
  break;
    

  case 55:
  if (yyn == 55)
    /* "agu.y":363  */ /* lalr1.java:489  */
    {
    ((ArrayList<FuncArg> )(yystack.valueAt (2-(1)))).add(((FuncArg)(yystack.valueAt (2-(2)))));
    yyval = ((ArrayList<FuncArg> )(yystack.valueAt (2-(1))));
};
  break;
    

  case 56:
  if (yyn == 56)
    /* "agu.y":370  */ /* lalr1.java:489  */
    {
    yyval = new FuncArg(((String)(yystack.valueAt (3-(1)))),((TypeTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 57:
  if (yyn == 57)
    /* "agu.y":376  */ /* lalr1.java:489  */
    {
    yyval = new StmtWhile(((ASTree)(yystack.valueAt (3-(2)))),((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 58:
  if (yyn == 58)
    /* "agu.y":382  */ /* lalr1.java:489  */
    {
    yyval = new CondExpr(((ASTree)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 59:
  if (yyn == 59)
    /* "agu.y":388  */ /* lalr1.java:489  */
    {
    yyval = new BodyExpr(((ASTree)(yystack.valueAt (1-(1)))), "Do", (yyloc).begin);
};
  break;
    

  case 60:
  if (yyn == 60)
    /* "agu.y":395  */ /* lalr1.java:489  */
    {
    yyval = ((ASTree)(yystack.valueAt (2-(2))));
};
  break;
    

  case 61:
  if (yyn == 61)
    /* "agu.y":399  */ /* lalr1.java:489  */
    {
    yyval = ((ASTree)(yystack.valueAt (2-(2))));
};
  break;
    

  case 62:
  if (yyn == 62)
    /* "agu.y":403  */ /* lalr1.java:489  */
    {
    yyval = ((DeclTree)(yystack.valueAt (2-(2))));
};
  break;
    

  case 63:
  if (yyn == 63)
    /* "agu.y":409  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<ASTree>();
};
  break;
    

  case 65:
  if (yyn == 65)
    /* "agu.y":417  */ /* lalr1.java:489  */
    {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add(((ASTree)(yystack.valueAt (1-(1)))));
    yyval = aux;
};
  break;
    

  case 66:
  if (yyn == 66)
    /* "agu.y":423  */ /* lalr1.java:489  */
    {
    ((ArrayList<ASTree> )(yystack.valueAt (3-(1)))).add(((ASTree)(yystack.valueAt (3-(3)))));
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 67:
  if (yyn == 67)
    /* "agu.y":430  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<ASTree>();
};
  break;
    

  case 68:
  if (yyn == 68)
    /* "agu.y":434  */ /* lalr1.java:489  */
    {
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (1-(1))));
};
  break;
    

  case 69:
  if (yyn == 69)
    /* "agu.y":441  */ /* lalr1.java:489  */
    {
    yyval = new FuncCall(((ASTree)(yystack.valueAt (2-(1)))),((ArrayList<ASTree> )(yystack.valueAt (2-(2)))), (yyloc).begin);
};
  break;
    

  case 72:
  if (yyn == 72)
    /* "agu.y":451  */ /* lalr1.java:489  */
    {
    yyval = new DefIf(((ASTree)(yystack.valueAt (6-(2)))),((ASTree)(yystack.valueAt (6-(4)))),((ASTree)(yystack.valueAt (6-(6)))), (yyloc).begin);
};
  break;
    

  case 73:
  if (yyn == 73)
    /* "agu.y":455  */ /* lalr1.java:489  */
    {
    yyval = new DefWhen(((ASTree)(yystack.valueAt (4-(2)))),((ASTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 74:
  if (yyn == 74)
    /* "agu.y":459  */ /* lalr1.java:489  */
    {
    yyval = new DefWhen(((ASTree)(yystack.valueAt (4-(2)))),((ASTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 75:
  if (yyn == 75)
    /* "agu.y":463  */ /* lalr1.java:489  */
    {
    yyval = new LetExprDef(((ArrayList<ASTree> )(yystack.valueAt (4-(2)))),((ASTree)(yystack.valueAt (4-(4)))), (yyloc).begin);
};
  break;
    

  case 76:
  if (yyn == 76)
    /* "agu.y":469  */ /* lalr1.java:489  */
    {
    yyval = new BodyExpr(((ASTree)(yystack.valueAt (1-(1)))), "Then", (yyloc).begin);
};
  break;
    

  case 77:
  if (yyn == 77)
    /* "agu.y":475  */ /* lalr1.java:489  */
    {
    yyval = new BodyExpr(((ASTree)(yystack.valueAt (1-(1)))), "Else", (yyloc).begin);
};
  break;
    

  case 78:
  if (yyn == 78)
    /* "agu.y":481  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"&&",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 79:
  if (yyn == 79)
    /* "agu.y":485  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"||",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 81:
  if (yyn == 81)
    /* "agu.y":493  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"<",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 82:
  if (yyn == 82)
    /* "agu.y":497  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"<=",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 83:
  if (yyn == 83)
    /* "agu.y":501  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"==",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 84:
  if (yyn == 84)
    /* "agu.y":505  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"/=",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 85:
  if (yyn == 85)
    /* "agu.y":509  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),">=",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 86:
  if (yyn == 86)
    /* "agu.y":513  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),">",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 88:
  if (yyn == 88)
    /* "agu.y":521  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"+",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 89:
  if (yyn == 89)
    /* "agu.y":525  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"-",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 91:
  if (yyn == 91)
    /* "agu.y":533  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"*",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 92:
  if (yyn == 92)
    /* "agu.y":537  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"/",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 93:
  if (yyn == 93)
    /* "agu.y":541  */ /* lalr1.java:489  */
    {
    yyval = new BinaryExpr(((ASTree)(yystack.valueAt (3-(1)))),"%",((ASTree)(yystack.valueAt (3-(3)))), (yyloc).begin);
};
  break;
    

  case 95:
  if (yyn == 95)
    /* "agu.y":549  */ /* lalr1.java:489  */
    {
    yyval = new UnaryExpr("NOT",((ASTree)(yystack.valueAt (2-(2)))), (yyloc).begin);
};
  break;
    

  case 96:
  if (yyn == 96)
    /* "agu.y":553  */ /* lalr1.java:489  */
    {
    yyval = new UnaryExpr("+",((ASTree)(yystack.valueAt (2-(2)))), (yyloc).begin);
};
  break;
    

  case 97:
  if (yyn == 97)
    /* "agu.y":557  */ /* lalr1.java:489  */
    {
    yyval = new UnaryExpr("-",((ASTree)(yystack.valueAt (2-(2)))), (yyloc).begin);
};
  break;
    

  case 98:
  if (yyn == 98)
    /* "agu.y":561  */ /* lalr1.java:489  */
    {
    yyval = new DirectAccess(((String)(yystack.valueAt (2-(2)))), (yyloc).begin);
};
  break;
    

  case 99:
  if (yyn == 99)
    /* "agu.y":565  */ /* lalr1.java:489  */
    {
    yyval = new IdExpr(((String)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 100:
  if (yyn == 100)
    /* "agu.y":569  */ /* lalr1.java:489  */
    {
    yyval = new IntExpr(((String)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 101:
  if (yyn == 101)
    /* "agu.y":573  */ /* lalr1.java:489  */
    {
    yyval = new CharExpr(((StringBuffer)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 102:
  if (yyn == 102)
    /* "agu.y":577  */ /* lalr1.java:489  */
    {
    yyval = new StringExpr(((StringBuffer)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 103:
  if (yyn == 103)
    /* "agu.y":581  */ /* lalr1.java:489  */
    {
    yyval = new BoolExpr(((String)(yystack.valueAt (1-(1)))), (yyloc).begin);
};
  break;
    

  case 104:
  if (yyn == 104)
    /* "agu.y":585  */ /* lalr1.java:489  */
    {
    yyval = new ParExpr(((ASTree)(yystack.valueAt (3-(2)))), (yyloc).begin);
};
  break;
    

  case 105:
  if (yyn == 105)
    /* "agu.y":589  */ /* lalr1.java:489  */
    {
    yyval = new ObjDef(((String)(yystack.valueAt (4-(1)))),((ArrayList<ASTree> )(yystack.valueAt (4-(3)))), (yyloc).begin);
};
  break;
    

  case 106:
  if (yyn == 106)
    /* "agu.y":595  */ /* lalr1.java:489  */
    {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add(((ASTree)(yystack.valueAt (1-(1)))));
    yyval = aux;
};
  break;
    

  case 107:
  if (yyn == 107)
    /* "agu.y":601  */ /* lalr1.java:489  */
    {
    ((ArrayList<ASTree> )(yystack.valueAt (2-(1)))).add(((ASTree)(yystack.valueAt (2-(2)))));
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (2-(1))));
};
  break;
    

  case 108:
  if (yyn == 108)
    /* "agu.y":608  */ /* lalr1.java:489  */
    {
    ArrayList<ASTree> aux = new ArrayList<ASTree>();
    aux.add(((ASTree)(yystack.valueAt (1-(1)))));
    yyval = aux;
};
  break;
    

  case 109:
  if (yyn == 109)
    /* "agu.y":614  */ /* lalr1.java:489  */
    {
    ((ArrayList<ASTree> )(yystack.valueAt (3-(1)))).add(((ASTree)(yystack.valueAt (3-(3)))));
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 110:
  if (yyn == 110)
    /* "agu.y":621  */ /* lalr1.java:489  */
    {
    yyval = new LetExpr(((String)(yystack.valueAt (5-(1)))),((TypeTree)(yystack.valueAt (5-(3)))),((ASTree)(yystack.valueAt (5-(5)))), (yyloc).begin);
};
  break;
    

  case 111:
  if (yyn == 111)
    /* "agu.y":628  */ /* lalr1.java:489  */
    {
    yyval = new BlockExpr(((ArrayList<ASTree> )(yystack.valueAt (3-(2)))), (yyloc).begin);
};
  break;
    

  case 112:
  if (yyn == 112)
    /* "agu.y":634  */ /* lalr1.java:489  */
    {
    yyval = new ArrayList<ASTree>();
};
  break;
    

  case 113:
  if (yyn == 113)
    /* "agu.y":638  */ /* lalr1.java:489  */
    {
    ((ArrayList<ASTree> )(yystack.valueAt (3-(1)))).add(((ASTree)(yystack.valueAt (3-(2)))));
    yyval = ((ArrayList<ASTree> )(yystack.valueAt (3-(1))));
};
  break;
    

  case 114:
  if (yyn == 114)
    /* "agu.y":645  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 115:
  if (yyn == 115)
    /* "agu.y":649  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 116:
  if (yyn == 116)
    /* "agu.y":653  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((StmtWhile)(yystack.valueAt (1-(1))));
};
  break;
    

  case 117:
  if (yyn == 117)
    /* "agu.y":657  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((DeclTree)(yystack.valueAt (1-(1))));
};
  break;
    

  case 118:
  if (yyn == 118)
    /* "agu.y":661  */ /* lalr1.java:489  */
    {
    yyval = (ASTree) ((ASTree)(yystack.valueAt (1-(1))));
};
  break;
    


/* "Parser.java":1463  */ /* lalr1.java:489  */
        default: break;
      }

    yy_symbol_print ("-> $$ =", yyr1_[yyn], yyval, yyloc);

    yystack.pop (yylen);
    yylen = 0;

    /* Shift the result of the reduction.  */
    int yystate = yy_lr_goto_state_ (yystack.stateAt (0), yyr1_[yyn]);
    yystack.push (yystate, yyval, yyloc);
    return YYNEWSTATE;
  }


  /* Return YYSTR after stripping away unnecessary quotes and
     backslashes, so that it's suitable for yyerror.  The heuristic is
     that double-quoting is unnecessary unless the string contains an
     apostrophe, a comma, or backslash (other than backslash-backslash).
     YYSTR is taken from yytname.  */
  private final String yytnamerr_ (String yystr)
  {
    if (yystr.charAt (0) == '"')
      {
        StringBuffer yyr = new StringBuffer ();
        strip_quotes: for (int i = 1; i < yystr.length (); i++)
          switch (yystr.charAt (i))
            {
            case '\'':
            case ',':
              break strip_quotes;

            case '\\':
              if (yystr.charAt(++i) != '\\')
                break strip_quotes;
              /* Fall through.  */
            default:
              yyr.append (yystr.charAt (i));
              break;

            case '"':
              return yyr.toString ();
            }
      }
    else if (yystr.equals ("$end"))
      return "end of input";

    return yystr;
  }


  /*--------------------------------.
  | Print this symbol on YYOUTPUT.  |
  `--------------------------------*/

  private void yy_symbol_print (String s, int yytype,
                                 Object yyvaluep                                 , Object yylocationp)
  {
    if (yydebug > 0)
    yycdebug (s + (yytype < yyntokens_ ? " token " : " nterm ")
              + yytname_[yytype] + " ("
              + yylocationp + ": "
              + (yyvaluep == null ? "(null)" : yyvaluep.toString ()) + ")");
  }


  /**
   * Parse input from the scanner that was specified at object construction
   * time.  Return whether the end of the input was reached successfully.
   *
   * @return <tt>true</tt> if the parsing succeeds.  Note that this does not
   *          imply that there were no syntax errors.
   */
   public boolean parse () throws java.io.IOException

  {
    /* @$.  */
    Location yyloc;


    /* Lookahead and lookahead in internal form.  */
    int yychar = yyempty_;
    int yytoken = 0;

    /* State.  */
    int yyn = 0;
    int yylen = 0;
    int yystate = 0;
    YYStack yystack = new YYStack ();
    int label = YYNEWSTATE;

    /* Error handling.  */
    int yynerrs_ = 0;
    /* The location where the error started.  */
    Location yyerrloc = null;

    /* Location. */
    Location yylloc = new Location (null, null);

    /* Semantic value of the lookahead.  */
    Object yylval = null;

    yycdebug ("Starting parse\n");
    yyerrstatus_ = 0;

    /* Initialize the stack.  */
    yystack.push (yystate, yylval , yylloc);



    for (;;)
      switch (label)
      {
        /* New state.  Unlike in the C/C++ skeletons, the state is already
           pushed when we come here.  */
      case YYNEWSTATE:
        yycdebug ("Entering state " + yystate + "\n");
        if (yydebug > 0)
          yystack.print (yyDebugStream);

        /* Accept?  */
        if (yystate == yyfinal_)
          return true;

        /* Take a decision.  First try without lookahead.  */
        yyn = yypact_[yystate];
        if (yy_pact_value_is_default_ (yyn))
          {
            label = YYDEFAULT;
            break;
          }

        /* Read a lookahead token.  */
        if (yychar == yyempty_)
          {


            yycdebug ("Reading a token: ");
            yychar = yylexer.yylex ();
            yylval = yylexer.getLVal ();
            yylloc = new Location (yylexer.getStartPos (),
                            yylexer.getEndPos ());

          }

        /* Convert token to internal form.  */
        if (yychar <= Lexer.EOF)
          {
            yychar = yytoken = Lexer.EOF;
            yycdebug ("Now at end of input.\n");
          }
        else
          {
            yytoken = yytranslate_ (yychar);
            yy_symbol_print ("Next token is", yytoken,
                             yylval, yylloc);
          }

        /* If the proper action on seeing token YYTOKEN is to reduce or to
           detect an error, take that action.  */
        yyn += yytoken;
        if (yyn < 0 || yylast_ < yyn || yycheck_[yyn] != yytoken)
          label = YYDEFAULT;

        /* <= 0 means reduce or error.  */
        else if ((yyn = yytable_[yyn]) <= 0)
          {
            if (yy_table_value_is_error_ (yyn))
              label = YYERRLAB;
            else
              {
                yyn = -yyn;
                label = YYREDUCE;
              }
          }

        else
          {
            /* Shift the lookahead token.  */
            yy_symbol_print ("Shifting", yytoken,
                             yylval, yylloc);

            /* Discard the token being shifted.  */
            yychar = yyempty_;

            /* Count tokens shifted since error; after three, turn off error
               status.  */
            if (yyerrstatus_ > 0)
              --yyerrstatus_;

            yystate = yyn;
            yystack.push (yystate, yylval, yylloc);
            label = YYNEWSTATE;
          }
        break;

      /*-----------------------------------------------------------.
      | yydefault -- do the default action for the current state.  |
      `-----------------------------------------------------------*/
      case YYDEFAULT:
        yyn = yydefact_[yystate];
        if (yyn == 0)
          label = YYERRLAB;
        else
          label = YYREDUCE;
        break;

      /*-----------------------------.
      | yyreduce -- Do a reduction.  |
      `-----------------------------*/
      case YYREDUCE:
        yylen = yyr2_[yyn];
        label = yyaction (yyn, yystack, yylen);
        yystate = yystack.stateAt (0);
        break;

      /*------------------------------------.
      | yyerrlab -- here on detecting error |
      `------------------------------------*/
      case YYERRLAB:
        /* If not already recovering from an error, report this error.  */
        if (yyerrstatus_ == 0)
          {
            ++yynerrs_;
            if (yychar == yyempty_)
              yytoken = yyempty_;
            yyerror (yylloc, yysyntax_error (yystate, yytoken));
          }

        yyerrloc = yylloc;
        if (yyerrstatus_ == 3)
          {
        /* If just tried and failed to reuse lookahead token after an
         error, discard it.  */

        if (yychar <= Lexer.EOF)
          {
          /* Return failure if at end of input.  */
          if (yychar == Lexer.EOF)
            return false;
          }
        else
            yychar = yyempty_;
          }

        /* Else will try to reuse lookahead token after shifting the error
           token.  */
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------.
      | errorlab -- error raised explicitly by YYERROR.  |
      `-------------------------------------------------*/
      case YYERROR:

        yyerrloc = yystack.locationAt (yylen - 1);
        /* Do not reclaim the symbols of the rule which action triggered
           this YYERROR.  */
        yystack.pop (yylen);
        yylen = 0;
        yystate = yystack.stateAt (0);
        label = YYERRLAB1;
        break;

      /*-------------------------------------------------------------.
      | yyerrlab1 -- common code for both syntax error and YYERROR.  |
      `-------------------------------------------------------------*/
      case YYERRLAB1:
        yyerrstatus_ = 3;       /* Each real token shifted decrements this.  */

        for (;;)
          {
            yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                yyn += yyterror_;
                if (0 <= yyn && yyn <= yylast_ && yycheck_[yyn] == yyterror_)
                  {
                    yyn = yytable_[yyn];
                    if (0 < yyn)
                      break;
                  }
              }

            /* Pop the current state because it cannot handle the
             * error token.  */
            if (yystack.height == 0)
              return false;

            yyerrloc = yystack.locationAt (0);
            yystack.pop ();
            yystate = yystack.stateAt (0);
            if (yydebug > 0)
              yystack.print (yyDebugStream);
          }

        if (label == YYABORT)
            /* Leave the switch.  */
            break;


        /* Muck with the stack to setup for yylloc.  */
        yystack.push (0, null, yylloc);
        yystack.push (0, null, yyerrloc);
        yyloc = yylloc (yystack, 2);
        yystack.pop (2);

        /* Shift the error token.  */
        yy_symbol_print ("Shifting", yystos_[yyn],
                         yylval, yyloc);

        yystate = yyn;
        yystack.push (yyn, yylval, yyloc);
        label = YYNEWSTATE;
        break;

        /* Accept.  */
      case YYACCEPT:
        return true;

        /* Abort.  */
      case YYABORT:
        return false;
      }
}




  // Generate an error message.
  private String yysyntax_error (int yystate, int tok)
  {
    if (yyErrorVerbose)
      {
        /* There are many possibilities here to consider:
           - If this state is a consistent state with a default action,
             then the only way this function was invoked is if the
             default action is an error action.  In that case, don't
             check for expected tokens because there are none.
           - The only way there can be no lookahead present (in tok) is
             if this state is a consistent state with a default action.
             Thus, detecting the absence of a lookahead is sufficient to
             determine that there is no unexpected or expected token to
             report.  In that case, just report a simple "syntax error".
           - Don't assume there isn't a lookahead just because this
             state is a consistent state with a default action.  There
             might have been a previous inconsistent state, consistent
             state with a non-default action, or user semantic action
             that manipulated yychar.  (However, yychar is currently out
             of scope during semantic actions.)
           - Of course, the expected token list depends on states to
             have correct lookahead information, and it depends on the
             parser not to perform extra reductions after fetching a
             lookahead from the scanner and before detecting a syntax
             error.  Thus, state merging (from LALR or IELR) and default
             reductions corrupt the expected token list.  However, the
             list is correct for canonical LR with one exception: it
             will still contain any token that will not be accepted due
             to an error action in a later state.
        */
        if (tok != yyempty_)
          {
            /* FIXME: This method of building the message is not compatible
               with internationalization.  */
            StringBuffer res =
              new StringBuffer ("syntax error, unexpected ");
            res.append (yytnamerr_ (yytname_[tok]));
            int yyn = yypact_[yystate];
            if (!yy_pact_value_is_default_ (yyn))
              {
                /* Start YYX at -YYN if negative to avoid negative
                   indexes in YYCHECK.  In other words, skip the first
                   -YYN actions for this state because they are default
                   actions.  */
                int yyxbegin = yyn < 0 ? -yyn : 0;
                /* Stay within bounds of both yycheck and yytname.  */
                int yychecklim = yylast_ - yyn + 1;
                int yyxend = yychecklim < yyntokens_ ? yychecklim : yyntokens_;
                int count = 0;
                for (int x = yyxbegin; x < yyxend; ++x)
                  if (yycheck_[x + yyn] == x && x != yyterror_
                      && !yy_table_value_is_error_ (yytable_[x + yyn]))
                    ++count;
                if (count < 5)
                  {
                    count = 0;
                    for (int x = yyxbegin; x < yyxend; ++x)
                      if (yycheck_[x + yyn] == x && x != yyterror_
                          && !yy_table_value_is_error_ (yytable_[x + yyn]))
                        {
                          res.append (count++ == 0 ? ", expecting " : " or ");
                          res.append (yytnamerr_ (yytname_[x]));
                        }
                  }
              }
            return res.toString ();
          }
      }

    return "syntax error";
  }

  /**
   * Whether the given <code>yypact_</code> value indicates a defaulted state.
   * @param yyvalue   the value to check
   */
  private static boolean yy_pact_value_is_default_ (int yyvalue)
  {
    return yyvalue == yypact_ninf_;
  }

  /**
   * Whether the given <code>yytable_</code>
   * value indicates a syntax error.
   * @param yyvalue the value to check
   */
  private static boolean yy_table_value_is_error_ (int yyvalue)
  {
    return yyvalue == yytable_ninf_;
  }

  private static final short yypact_ninf_ = -150;
  private static final short yytable_ninf_ = -46;

  /* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
  private static final short yypact_[] = yypact_init();
  private static final short[] yypact_init()
  {
    return new short[]
    {
    -150,    65,   162,  -150,   -32,     1,  -150,    45,    43,    52,
     276,   276,   276,   294,  -150,    54,  -150,  -150,   294,   294,
     276,    64,  -150,  -150,    66,  -150,  -150,  -150,  -150,  -150,
     112,  -150,  -150,   276,  -150,    71,    56,    98,    48,  -150,
    -150,    51,  -150,     7,    84,     5,   -12,  -150,   119,   123,
     132,   276,   181,   129,  -150,   276,  -150,  -150,   200,   114,
    -150,   134,   276,   276,   294,   294,   294,   294,   294,   294,
     294,   294,   294,   294,   294,   294,   294,    32,    14,  -150,
     276,    32,   117,    32,    15,  -150,   232,   119,   120,  -150,
     232,   232,   109,  -150,  -150,   124,   121,   276,  -150,   276,
     131,   276,    56,    56,    98,    98,    98,    98,    98,    98,
      48,    48,  -150,  -150,  -150,  -150,    32,   125,   276,   276,
      41,    32,    44,    32,   128,  -150,  -150,  -150,   276,  -150,
    -150,    32,  -150,   147,   276,  -150,  -150,   276,  -150,   276,
    -150,   276,    68,   133,  -150,    21,    32,   276,   276,   125,
     276,   125,   232,     8,    55,   232,   276,  -150,  -150,   136,
     140,  -150,   159,    24,  -150,   125,   276,   254,  -150,   161,
    -150,  -150,  -150,  -150,  -150,   276,   141,   276,  -150,  -150,
    -150,    87,   152,   166,  -150,  -150,  -150,  -150,   164,  -150,
     276,  -150,    32,   276,    29,   125,   178,   124,   175,  -150,
    -150,  -150,   169,  -150,  -150,    28,  -150,    32,    73,   276,
     276
    };
  }

/* YYDEFACT[STATE-NUM] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE does not specify something else to do.  Zero
   means the default is an error.  */
  private static final byte yydefact_[] = yydefact_init();
  private static final byte[] yydefact_init()
  {
    return new byte[]
    {
       3,     0,     0,     1,     0,     0,    28,    24,     0,     0,
       0,     0,     0,     0,    99,     0,   100,   101,     0,     0,
       0,     0,   102,   103,     0,     6,     7,     8,    10,    11,
       0,     9,    12,    13,    71,    70,    80,    87,    90,    94,
       5,     0,    24,     0,     0,     0,     0,   108,     0,     0,
       0,    58,     0,     0,    95,    67,    96,    97,     0,    98,
       4,     0,   106,    69,     0,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,    25,
       0,     0,     0,     0,    52,    54,     0,     0,     0,    98,
       0,     0,     0,    57,    59,    68,     0,    65,   104,     0,
      29,   107,    78,    79,    83,    84,    81,    86,    82,    85,
      88,    89,    91,    92,    93,    14,     0,    17,     0,    21,
      18,     0,     0,     0,     0,    55,   112,    75,    50,    51,
     109,     0,    76,     0,    73,    74,    62,    60,    61,     0,
     105,    23,    40,     0,    30,     0,     0,    22,     0,    56,
       0,    53,     0,     0,     0,     0,    66,    47,    46,     0,
      41,    42,     0,     0,    16,    15,    19,   110,    49,     0,
     111,   114,   115,   117,   116,   118,     0,     0,    77,    72,
      39,    45,     0,     0,    34,    26,    31,    32,     0,   113,
     110,    43,     0,    63,     0,    44,     0,    64,     0,    33,
      37,    36,     0,    38,    48,     0,    35,     0,     0,     0,
      20
    };
  }

/* YYPGOTO[NTERM-NUM].  */
  private static final short yypgoto_[] = yypgoto_init();
  private static final short[] yypgoto_init()
  {
    return new short[]
    {
    -150,  -150,  -150,  -150,   -80,  -150,  -149,  -148,   -81,   171,
    -150,  -150,  -150,  -150,  -150,  -150,  -150,  -150,  -150,  -150,
      57,  -150,  -150,    31,   -88,  -150,  -150,   142,    77,   206,
    -150,  -150,  -150,    39,  -150,    -2,  -150,  -150,  -150,  -150,
      60,    37,    62,    -6,  -150,  -150,   146,    49,  -150,  -150
    };
  }

/* YYDEFGOTO[NTERM-NUM].  */
  private static final short yydefgoto_[] = yydefgoto_init();
  private static final short[] yydefgoto_init()
  {
    return new short[]
    {
      -1,     1,     2,    24,   117,    25,    26,    27,    28,    43,
      29,    30,   143,   185,   186,   194,   202,   144,   159,   160,
     161,   162,   187,    31,   127,   124,    84,    85,    32,    50,
      93,    94,   196,    95,    96,    62,    34,   133,   179,    35,
      36,    37,    38,    39,    63,    46,    47,   129,   153,   176
    };
  }

/* YYTABLE[YYPACT[STATE-NUM]] -- What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule whose
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
  private static final short yytable_[] = yytable_init();
  private static final short[] yytable_init()
  {
    return new short[]
    {
      33,   120,   132,   122,   171,   172,    86,    54,    51,    52,
      51,   136,    56,    57,     7,    40,     8,   169,    58,    10,
      87,    41,    11,    12,    82,    13,    79,    14,    15,    16,
      17,    18,    19,    79,    82,    20,   145,     8,   198,   170,
      83,   149,    21,   151,   183,   200,   201,    82,    80,   164,
     123,   154,   115,    97,   184,   118,    22,   146,    23,   116,
     199,   101,    44,   207,   168,     3,   165,   178,   112,   113,
     114,    45,   173,    74,    75,   157,   158,   146,   119,    42,
     146,    55,   148,    59,   128,   150,    76,   -45,   128,   134,
     137,   146,    77,    66,   157,   158,   177,   141,    67,    68,
      69,    70,    71,   104,   105,   106,   107,   108,   109,   146,
      64,    65,   195,    60,   209,     7,   147,    61,    48,    81,
      10,    72,    73,    11,   102,   103,    13,   208,    14,    15,
      16,    17,    18,    19,   110,   111,    20,   156,    88,   126,
     135,   138,    89,    21,    90,    92,   166,    99,   167,   140,
     128,   175,   121,   128,   100,   131,   139,    22,   142,    23,
     155,   146,    -2,     4,   180,     5,     6,   -27,     7,   152,
       8,     9,   181,    10,   163,   190,    11,    12,   182,    13,
     188,    14,    15,    16,    17,    18,    19,   192,   189,    20,
      48,    97,    10,   193,   205,    11,    21,    91,    13,    83,
      14,    15,    16,    17,    18,    19,   204,   210,    20,    48,
      22,    10,    23,    78,    11,    49,   206,    13,    53,    14,
      15,    16,    17,    18,    19,   203,   125,    20,    98,    22,
     174,    23,   197,   130,    49,     0,     0,     0,   191,     0,
       0,    48,     0,    10,     0,     0,    11,     0,    22,    13,
      23,    14,    15,    16,    17,    18,    19,     0,     0,    20,
       0,     0,   126,    48,     0,    10,    49,     0,    11,     0,
       0,    13,     0,    14,    15,    16,    17,    18,    19,     0,
      22,    20,    23,     0,     0,    48,     0,    10,    49,     0,
      11,     0,     0,    13,     0,    14,    15,    16,    17,    18,
      19,   -20,    22,    20,    23,     0,     0,     0,     0,     0,
      49,    13,     0,    14,    15,    16,    17,    18,    19,     0,
       0,    20,     0,     0,    22,     0,    23,     0,    49,     0,
       0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
       0,     0,    22,     0,    23
    };
  }

private static final short yycheck_[] = yycheck_init();
  private static final short[] yycheck_init()
  {
    return new short[]
    {
       2,    81,    90,    83,   153,   153,    18,    13,    10,    11,
      12,    92,    18,    19,     6,    47,     8,     9,    20,    11,
      32,    20,    14,    15,    19,    17,    19,    19,    20,    21,
      22,    23,    24,    19,    19,    27,   116,     8,     9,    31,
      35,   121,    34,   123,    20,   194,   194,    19,    41,    28,
      35,   131,    20,    55,    30,    41,    48,    36,    50,    27,
      31,    63,    19,    35,   152,     0,   146,   155,    74,    75,
      76,    19,   153,    25,    26,     7,     8,    36,    80,    34,
      36,    27,    41,    19,    86,    41,    38,    19,    90,    91,
      92,    36,    41,    37,     7,     8,    41,    99,    42,    43,
      44,    45,    46,    66,    67,    68,    69,    70,    71,    36,
      39,    40,   192,    47,    41,     6,   118,     5,     9,    35,
      11,    23,    24,    14,    64,    65,    17,   207,    19,    20,
      21,    22,    23,    24,    72,    73,    27,   139,    19,    30,
      91,    92,    19,    34,    12,    16,   148,    33,   150,    28,
     152,   153,    35,   155,    20,    35,    32,    48,    27,    50,
      13,    36,     0,     1,    28,     3,     4,     5,     6,    41,
       8,     9,    32,    11,    41,   177,    14,    15,    19,    17,
      19,    19,    20,    21,    22,    23,    24,    35,    47,    27,
       9,   193,    11,    27,    19,    14,    34,    16,    17,    35,
      19,    20,    21,    22,    23,    24,    28,   209,    27,     9,
      48,    11,    50,    42,    14,    34,    47,    17,    12,    19,
      20,    21,    22,    23,    24,   194,    84,    27,    28,    48,
     153,    50,   193,    87,    34,    -1,    -1,    -1,   181,    -1,
      -1,     9,    -1,    11,    -1,    -1,    14,    -1,    48,    17,
      50,    19,    20,    21,    22,    23,    24,    -1,    -1,    27,
      -1,    -1,    30,     9,    -1,    11,    34,    -1,    14,    -1,
      -1,    17,    -1,    19,    20,    21,    22,    23,    24,    -1,
      48,    27,    50,    -1,    -1,     9,    -1,    11,    34,    -1,
      14,    -1,    -1,    17,    -1,    19,    20,    21,    22,    23,
      24,    47,    48,    27,    50,    -1,    -1,    -1,    -1,    -1,
      34,    17,    -1,    19,    20,    21,    22,    23,    24,    -1,
      -1,    27,    -1,    -1,    48,    -1,    50,    -1,    34,    -1,
      -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,
      -1,    -1,    48,    -1,    50
    };
  }

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
  private static final byte yystos_[] = yystos_init();
  private static final byte[] yystos_init()
  {
    return new byte[]
    {
       0,    52,    53,     0,     1,     3,     4,     6,     8,     9,
      11,    14,    15,    17,    19,    20,    21,    22,    23,    24,
      27,    34,    48,    50,    54,    56,    57,    58,    59,    61,
      62,    74,    79,    86,    87,    90,    91,    92,    93,    94,
      47,    20,    34,    60,    19,    19,    96,    97,     9,    34,
      80,    86,    86,    80,    94,    27,    94,    94,    86,    19,
      47,     5,    86,    95,    39,    40,    37,    42,    43,    44,
      45,    46,    23,    24,    25,    26,    38,    41,    60,    19,
      41,    35,    19,    35,    77,    78,    18,    32,    19,    19,
      12,    16,    16,    81,    82,    84,    85,    86,    28,    33,
      20,    86,    91,    91,    92,    92,    92,    92,    92,    92,
      93,    93,    94,    94,    94,    20,    27,    55,    41,    86,
      55,    35,    55,    35,    76,    78,    30,    75,    86,    98,
      97,    35,    75,    88,    86,    98,    59,    86,    98,    32,
      28,    86,    27,    63,    68,    55,    36,    86,    41,    55,
      41,    55,    41,    99,    55,    13,    86,     7,     8,    69,
      70,    71,    72,    41,    28,    55,    86,    86,    75,     9,
      31,    57,    58,    59,    79,    86,   100,    41,    75,    89,
      28,    32,    19,    20,    30,    64,    65,    73,    19,    47,
      86,    71,    35,    27,    66,    55,    83,    84,     9,    31,
      57,    58,    67,    74,    28,    19,    47,    35,    55,    41,
      86
    };
  }

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
  private static final byte yyr1_[] = yyr1_init();
  private static final byte[] yyr1_init()
  {
    return new byte[]
    {
       0,    51,    52,    53,    53,    53,    54,    54,    54,    54,
      54,    54,    54,    54,    55,    55,    55,    56,    57,    57,
      58,    59,    59,    59,    60,    60,    61,    62,    62,    63,
      63,    64,    64,    65,    66,    66,    67,    67,    67,    68,
      69,    69,    70,    70,    71,    72,    72,    72,    73,    74,
      75,    75,    76,    76,    77,    77,    78,    79,    80,    81,
      82,    82,    82,    83,    83,    84,    84,    85,    85,    86,
      86,    86,    87,    87,    87,    87,    88,    89,    90,    90,
      90,    91,    91,    91,    91,    91,    91,    91,    92,    92,
      92,    93,    93,    93,    93,    94,    94,    94,    94,    94,
      94,    94,    94,    94,    94,    94,    95,    95,    96,    96,
      97,    98,    99,    99,   100,   100,   100,   100,   100
    };
  }

/* YYR2[YYN] -- Number of symbols on the right hand side of rule YYN.  */
  private static final byte yyr2_[] = yyr2_init();
  private static final byte[] yyr2_init()
  {
    return new byte[]
    {
       0,     2,     1,     0,     3,     3,     1,     1,     1,     1,
       1,     1,     1,     1,     1,     3,     3,     4,     4,     6,
       6,     4,     5,     4,     0,     2,     6,     0,     1,     0,
       1,     1,     1,     3,     0,     3,     1,     1,     1,     3,
       0,     1,     1,     3,     4,     0,     1,     1,     4,     6,
       1,     1,     0,     2,     1,     2,     3,     3,     1,     1,
       2,     2,     2,     0,     1,     1,     3,     0,     1,     2,
       1,     1,     6,     4,     4,     4,     1,     1,     3,     3,
       1,     3,     3,     3,     3,     3,     3,     1,     3,     3,
       1,     3,     3,     3,     1,     2,     2,     2,     2,     1,
       1,     1,     1,     1,     3,     4,     1,     2,     1,     3,
       5,     3,     0,     3,     1,     1,     1,     1,     1
    };
  }

  /* YYTOKEN_NUMBER[YYLEX-NUM] -- Internal symbol number corresponding
      to YYLEX-NUM.  */
  private static final short yytoken_number_[] = yytoken_number_init();
  private static final short[] yytoken_number_init()
  {
    return new short[]
    {
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294,
     295,   296,   297,   298,   299,   300,   301,   302,   303,   304,
     305
    };
  }

  /* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
     First, the terminals, then, starting at \a yyntokens_, nonterminals.  */
  private static final String yytname_[] = yytname_init();
  private static final String[] yytname_init()
  {
    return new String[]
    {
  "$end", "error", "$undefined", "ALIAS", "MUT", "CLASS", "SET", "VAL",
  "VAR", "LET", "DEF", "IF", "THEN", "ELSE", "WHEN", "WHILE", "DO", "NOT",
  "IN", "\"ID\"", "\"TYPE_ID\"", "\"INT\"", "\"CHAR\"", "\"+\"", "\"-\"",
  "\"*\"", "\"/\"", "\"(\"", "\")\"", "\";\"", "\"{\"", "\"}\"", "\",\"",
  "\"<-\"", "\"$\"", "\":\"", "\"->\"", "\"==\"", "\"%\"", "\"&&\"",
  "\"||\"", "\"=\"", "\"/=\"", "\"<\"", "\">\"", "\"<=\"", "\">=\"",
  "\"SEP\"", "\"STRING\"", "ERROR", "\"BOOL\"", "$accept", "program",
  "stmts_list", "decl", "type", "alias_decl", "var_decl", "const_decl",
  "assignment", "ID_list", "classdef", "opt_mut", "opt_constructor_args",
  "class_bodies", "classdef_body", "classdef_body_decl_list",
  "classdef_body_decl", "constructor_args", "opt_constr_arg_dec",
  "constr_arg_dec_list", "constr_arg_dec", "opt_var_or_val", "class_ctor",
  "func_decl", "body", "opt_colon_type", "func_arg_list", "func_arg",
  "while_stmt", "cond_expr", "body_expr_while", "do_expression",
  "opt_expression_comma_list", "expression_comma_list",
  "expression_comma_list_empty", "expression", "conditional_expr",
  "body_expr_then", "body_expr_else", "operator_expr", "expr1", "expr2",
  "expr3", "factor", "expression_list", "let_expr_comma_list", "let_expr",
  "block_expression", "block_stmt_list", "block_stmt", null
    };
  }

  /* YYRLINE[YYN] -- Source line where rule number YYN was defined.  */
  private static final short yyrline_[] = yyrline_init();
  private static final short[] yyrline_init()
  {
    return new short[]
    {
       0,   106,   106,   114,   119,   124,   128,   132,   136,   140,
     144,   148,   152,   156,   162,   166,   170,   176,   182,   186,
     192,   198,   202,   206,   212,   216,   223,   229,   233,   239,
     243,   247,   249,   253,   259,   263,   270,   272,   274,   278,
     284,   288,   292,   298,   305,   311,   315,   319,   325,   331,
     337,   341,   347,   351,   357,   363,   370,   376,   382,   388,
     395,   399,   403,   409,   413,   417,   423,   430,   434,   441,
     445,   447,   451,   455,   459,   463,   469,   475,   481,   485,
     489,   493,   497,   501,   505,   509,   513,   517,   521,   525,
     529,   533,   537,   541,   545,   549,   553,   557,   561,   565,
     569,   573,   577,   581,   585,   589,   595,   601,   608,   614,
     621,   628,   634,   638,   645,   649,   653,   657,   661
    };
  }


  // Report on the debug stream that the rule yyrule is going to be reduced.
  private void yy_reduce_print (int yyrule, YYStack yystack)
  {
    if (yydebug == 0)
      return;

    int yylno = yyrline_[yyrule];
    int yynrhs = yyr2_[yyrule];
    /* Print the symbols being reduced, and their result.  */
    yycdebug ("Reducing stack by rule " + (yyrule - 1)
              + " (line " + yylno + "), ");

    /* The symbols being reduced.  */
    for (int yyi = 0; yyi < yynrhs; yyi++)
      yy_symbol_print ("   $" + (yyi + 1) + " =",
                       yystos_[yystack.stateAt(yynrhs - (yyi + 1))],
                       ((yystack.valueAt (yynrhs-(yyi + 1)))),
                       yystack.locationAt (yynrhs-(yyi + 1)));
  }

  /* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
  private static final byte yytranslate_table_[] = yytranslate_table_init();
  private static final byte[] yytranslate_table_init()
  {
    return new byte[]
    {
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39,    40,    41,    42,    43,    44,
      45,    46,    47,    48,    49,    50
    };
  }

  private static final byte yytranslate_ (int t)
  {
    if (t >= 0 && t <= yyuser_token_number_max_)
      return yytranslate_table_[t];
    else
      return yyundef_token_;
  }

  private static final int yylast_ = 344;
  private static final int yynnts_ = 50;
  private static final int yyempty_ = -2;
  private static final int yyfinal_ = 3;
  private static final int yyterror_ = 1;
  private static final int yyerrcode_ = 256;
  private static final int yyntokens_ = 51;

  private static final int yyuser_token_number_max_ = 305;
  private static final int yyundef_token_ = 2;

/* User implementation code.  */
/* Unqualified %code blocks.  */
/* "agu.y":20  */ /* lalr1.java:1066  */

    /* el método parse retorna un boolean, que indica
       si el input es válido, pero no entrega el árbol.
       Para obtenerlo, se asigna a un atributo, y se recupera
       con un getter: */
    private ASTree tree;
    public ASTree getTree () {
        return tree;
    }

/* "Parser.java":2303  */ /* lalr1.java:1066  */

}

/* "agu.y":665  */ /* lalr1.java:1070  */

  public String getMessage() {
    if (!specialConstructor) {
      return super.getMessage();
    }
    StringBuilder expected = new StringBuilder();
    int maxSize = 0;
      for (int[] expectedTokenSequence : expectedTokenSequences) {
          if (maxSize < expectedTokenSequence.length) {
              maxSize = expectedTokenSequence.length;
          }
          for (int anExpectedTokenSequence : expectedTokenSequence) {
              expected.append(tokenImage[anExpectedTokenSequence]).append(' ');
          }
          if (expectedTokenSequence[expectedTokenSequence.length - 1] != 0) {
              expected.append("...");
          }
          expected.append(eol).append("    ");
      }
    String retval = "Encountered \"";
    Token tok = currentToken.next;
    for (int i = 0; i < maxSize; i++) {
      if (i != 0) retval += " ";
      if (tok.kind == 0) {
        retval += tokenImage[0];
        break;
      }
      retval += " " + tokenImage[tok.kind];
      retval += " \"";
      retval += add_escapes(tok.image);
      retval += " \"";
      tok = tok.next;
    }
    retval += "\" at line " + currentToken.next.beginLine + ", column " + currentToken.next.beginColumn;
    retval += "." + eol;
    if (expectedTokenSequences.length == 1) {
      retval += "Was expecting:" + eol + "    ";
    } else {
      retval += "Was expecting one of:" + eol + "    ";
    }
    retval += expected.toString();
    return retval;
  }

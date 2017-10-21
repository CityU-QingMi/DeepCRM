    public static String escapeHtml(String s) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) switch (c) {
          case '"':
            sb.append("&quot;");
            break;
          case '\'':
            // This case not supported by Commons Lang's escapeHtml
            sb.append("&apos;");
            break;
          case '&':
            sb.append("&amp;");
            break;
          case '<':
            sb.append("&lt;");
            break;
          case '>':
            sb.append("&gt;");
            break;
          default:
            sb.append(c);
        }
        return sb.toString();
    }

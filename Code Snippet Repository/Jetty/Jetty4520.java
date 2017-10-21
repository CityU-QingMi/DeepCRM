    private void writeModuleDetailLine(PrintWriter out, String line)
    {
        out.printf("  <TR>");
        StringBuilder escape = new StringBuilder();
        for(char c: line.toCharArray()) {
            switch(c) {
                case '<': escape.append("&lt;"); break;
                case '>': escape.append("&gt;"); break;
                default:
                    escape.append(c);
                    break;
            }
        }
        
        out.printf("<TD BGCOLOR=\"%s\" ALIGN=\"LEFT\">%s</TD></TR>%n",colorCellBg,escape.toString());
    }

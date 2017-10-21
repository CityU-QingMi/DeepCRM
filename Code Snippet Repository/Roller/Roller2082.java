    private void printDayInThisMonth(PrintWriter pw, Calendar cal, String url, String content) {
        if ( content!=null ) {
            pw.print("<td class=\"hCalendarDayCurrent"
                    +mClassSuffix+"\">");
            pw.print( content );
            pw.print("</td>");
        } else if (url!=null) {
            pw.print("<td class=\"hCalendarDayLinked"
                    +mClassSuffix+"\">");
            pw.print("<div class=\"hCalendarDayTitle"
                    +mClassSuffix+"\">");
            pw.print("<a href=\""+url+"\">");
            pw.print(cal.get(Calendar.DAY_OF_MONTH));
            pw.print("</a></div>");
            pw.print("</td>");
        } else {
            pw.print("<td class=\"hCalendarDay"
                    +mClassSuffix+"\">");
            pw.print("<div class=\"hCalendarDayTitle"
                    +mClassSuffix+"\">");
            pw.print(cal.get(Calendar.DAY_OF_MONTH));
            pw.print("</div>");
            pw.print("</td>");
        }
    }

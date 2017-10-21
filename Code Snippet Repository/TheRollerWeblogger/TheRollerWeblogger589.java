    public String getHtml(HttpServletRequest request) {
        
        String answer = "";
        
        HttpSession session = request.getSession(true);
        if (session.getAttribute("mathAnswer") == null) {
            // starting a new test
            Random ran = new Random();
            int value1 = ran.nextInt(10);
            int value2 = ran.nextInt(100);
            int sum = value1 + value2;
            session.setAttribute("mathValue1", value1);
            session.setAttribute("mathValue2", value2);
            session.setAttribute("mathAnswer", sum);
        } else {
            // preserve user's answer
            answer = request.getParameter("answer");
            answer = (answer == null) ? "" : answer;
        }
        
        // pull existing values out of session
        Integer value1o = (Integer)request.getSession().getAttribute("mathValue1");
        Integer value2o = (Integer)request.getSession().getAttribute("mathValue2");
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("<p>");
        sb.append(bundle.getString("comments.mathAuthenticatorQuestion"));
        sb.append("</p><p>");
        sb.append(value1o);
        sb.append(" + ");
        sb.append(value2o);
        sb.append(" = ");
        sb.append("<input name=\"answer\" value=\"");
        sb.append(answer);
        sb.append("\" /></p>");
        
        return sb.toString();
    }

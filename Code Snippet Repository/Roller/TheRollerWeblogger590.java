    public boolean authenticate(HttpServletRequest request) {
        
        boolean authentic = false;
        
        HttpSession session = request.getSession(false);
        String answerString = request.getParameter("answer");
        
        if (answerString != null && session != null) {
            try {
                int answer = Integer.parseInt(answerString);
                Integer sum = (Integer) session.getAttribute("mathAnswer");
                
                if (sum != null && answer == sum) {
                    authentic = true;
                    session.removeAttribute("mathAnswer");
                    session.removeAttribute("mathValue1");
                    session.removeAttribute("mathValue2");
                }
            } catch (NumberFormatException ignored) {
                // ignored ... someone is just really bad at math
            } catch (Exception e) {
                // unexpected
                mLogger.error(e);
            }
        }
        
        return authentic;
    }

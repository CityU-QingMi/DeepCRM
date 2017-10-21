    public int doEndTag() throws JspException {

        String text = "";
        if(bodyContent != null) {
            StringWriter body = new StringWriter();
            try {
                bodyContent.writeOut(body);
                text = body.toString();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }

        // first, try to evaluate the string and associated the result on var
        Object result = evaluateString( text );
        if ( result != null && this.var != null ) {
            pageContext.setAttribute(this.var, result);
        } else {
          // then, try to transform it
          text = changeString(text);
        
          if (this.var == null) {
            JspWriter writer = pageContext.getOut();
            try {
              writer.print(text);
            } catch (IOException e) {
              throw new JspException(e.toString());
            }
          } else {
            pageContext.setAttribute(this.var, text);
          }

        }

        return (EVAL_PAGE);
    }

    public int validate(WeblogEntryComment comment, RollerMessages messages) {
        StringBuilder sb = new StringBuilder();
        sb.append("blog=").append(
            WebloggerFactory.getWeblogger().getUrlStrategy().getWeblogURL(comment.getWeblogEntry().getWebsite(), null, true)).append("&");
        sb.append("user_ip="        ).append(comment.getRemoteHost()).append("&");
        sb.append("user_agent="     ).append(comment.getUserAgent()).append("&");
        sb.append("referrer="       ).append(comment.getReferrer()).append("&");
        sb.append("permalink="      ).append(comment.getWeblogEntry().getPermalink()).append("&");
        sb.append("comment_type="   ).append("comment").append("&");
        sb.append("comment_author=" ).append(comment.getName()).append("&");
        sb.append("comment_author_email=").append(comment.getEmail()).append("&");
        sb.append("comment_author_url="  ).append(comment.getUrl()).append("&");
        sb.append("comment_content="     ).append(comment.getContent());

        try {
            URL url = new URL("http://" + apikey + ".rest.akismet.com/1.1/comment-check");
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            conn.setRequestProperty("User_Agent", "Roller " + WebloggerFactory.getWeblogger().getVersion()); 
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf8"); 
            conn.setRequestProperty("Content-length", Integer.toString(sb.length()));

            OutputStreamWriter osr = new OutputStreamWriter(conn.getOutputStream());
            osr.write(sb.toString(), 0, sb.length());
            osr.flush();
            osr.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
            String response = br.readLine();
            if ("true".equals(response)) {
                messages.addError("comment.validator.akismetMessage");
                return 0;
            }
            else {
                return RollerConstants.PERCENT_100;
            }
        } catch (Exception e) {
            log.error("ERROR checking comment against Akismet", e);
        }
        // interpreting error as spam: better safe than sorry?
        return 0;
    }

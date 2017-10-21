        @Override
        public String getUriFromActionMapping(ActionMapping mapping) {
            String baseUri = super.getUriFromActionMapping(mapping);
            HttpSession session = ServletActionContext.getRequest().getSession();
            if (session.getAttribute("redBlue")==null) {
                // We are red
                session.setAttribute("redBlue", 0);
                return baseUri + "-red";
            } else {
                // We are blue
                session.removeAttribute("redBlue");
                return baseUri + "-blue";
            }
        }

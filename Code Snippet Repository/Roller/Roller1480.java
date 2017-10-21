    private static PingResult parseResult(Object obj) {
        // Deal with the fact that some buggy ping targets may not respond with the proper struct type.
        if (obj == null) {
            return new PingResult(null,null);
        }
        try {
            // normal case: response is a struct (represented as a Map) with Boolean flerror and String fields.
            Map result = (Map) obj;
            return new PingResult((Boolean) result.get("flerror"), (String) result.get("message"));
        } catch (Exception ex) {
            // exception case:  The caller responded with an unexpected type, though parsed at the basic XML RPC level.
            // This effectively assumes flerror = false, and sets message = obj.toString();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Invalid ping result of type: " + obj.getClass().getName() + "; proceeding with stand-in representative.");
            }
            return new PingResult(null,obj.toString());
        }
    }

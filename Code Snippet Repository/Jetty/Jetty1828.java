    public void cleanSubject(MessageInfo messageInfo, Subject subject) throws AuthException
    {
        // TODO apparently we either get the LoginCallback or the LoginService
        // but not both :-(
        // Set<LoginCallback> loginCallbacks =
        // subject.getPrivateCredentials(LoginCallback.class);
        // if (!loginCallbacks.isEmpty()) {
        // LoginCallback loginCallback = loginCallbacks.iterator().next();
        // }
        // try {
        // loginService.logout(subject);
        // } catch (ServerAuthException e) {
        // throw new AuthException(e.getMessage());
        // }
    }

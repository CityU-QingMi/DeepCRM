        private void forwardFailureComplete(HttpRequest request, Throwable requestFailure, Response response, Throwable responseFailure)
        {
            HttpConversation conversation = request.getConversation();
            conversation.updateResponseListeners(null);
            List<Response.ResponseListener> responseListeners = conversation.getResponseListeners();
            if (responseFailure == null)
                notifier.forwardSuccess(responseListeners, response);
            else
                notifier.forwardFailure(responseListeners, response, responseFailure);
            notifier.notifyComplete(responseListeners, new Result(request, requestFailure, response, responseFailure));
        }

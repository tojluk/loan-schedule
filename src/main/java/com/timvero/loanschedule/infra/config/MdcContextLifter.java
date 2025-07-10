package com.timvero.loanschedule.infra.config;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.util.context.Context;
import org.slf4j.MDC;
import java.util.Optional;

public class MdcContextLifter <T> implements CoreSubscriber<T> {
    private final CoreSubscriber<? super T> coreSubscriber;
    private final String mdcKey;
    private final String contextKey;

    public MdcContextLifter(CoreSubscriber<? super T> coreSubscriber,
                            String mdcKey,
                            String contextKey) {
        this.coreSubscriber = coreSubscriber;
        this.mdcKey = mdcKey;
        this.contextKey = contextKey;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        coreSubscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T t) {
        copyToMdc(coreSubscriber.currentContext());
        try {
            coreSubscriber.onNext(t);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        copyToMdc(coreSubscriber.currentContext());
        try {
            coreSubscriber.onError(throwable);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void onComplete() {
        copyToMdc(coreSubscriber.currentContext());
        try {
            coreSubscriber.onComplete();
        } finally {
            MDC.clear();
        }
    }

    @Override
    public Context currentContext() {
        return coreSubscriber.currentContext();
    }

    private void copyToMdc(Context context) {
        Optional.ofNullable(context.getOrDefault(contextKey, null))
                .ifPresent(value -> MDC.put(mdcKey, value.toString()));
    }
}

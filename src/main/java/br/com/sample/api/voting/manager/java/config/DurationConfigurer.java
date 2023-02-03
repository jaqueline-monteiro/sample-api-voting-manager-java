package br.com.sample.api.voting.manager.java.config;

@FunctionalInterface
public interface DurationConfigurer<T> {
    
    T applyDuration(Long arg0);
}

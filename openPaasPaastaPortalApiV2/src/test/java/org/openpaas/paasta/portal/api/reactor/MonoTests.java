package org.openpaas.paasta.portal.api.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactor.core.publisher.Mono;

/**
 * Created by mg on 2016-08-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MonoTests {

    @Test
    public void subscribe() {
        Mono<String> result = Mono.just("just subscribe");
        result.subscribe(x -> System.out.println("result=" + x));
    }
}

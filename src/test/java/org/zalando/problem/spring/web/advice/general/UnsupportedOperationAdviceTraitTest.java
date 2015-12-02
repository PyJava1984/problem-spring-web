package org.zalando.problem.spring.web.advice.general;

/*
 * #%L
 * Problem: Spring Web
 * %%
 * Copyright (C) 2015 Zalando SE
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;
import org.zalando.problem.spring.web.advice.AdviceTraitTest;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class UnsupportedOperationAdviceTraitTest implements AdviceTraitTest {

    @Test
    public void unsupportedOperation() throws Exception {
        mvc().perform(request(GET, "http://localhost/api/not-implemented"))
                .andExpect(status().isNotImplemented())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("http://httpstatus.es/501")))
                .andExpect(jsonPath("$.title", is("Not Implemented")))
                .andExpect(jsonPath("$.status", is(501)))
                .andExpect(jsonPath("$.detail", is("Not yet implemented")));
    }

}
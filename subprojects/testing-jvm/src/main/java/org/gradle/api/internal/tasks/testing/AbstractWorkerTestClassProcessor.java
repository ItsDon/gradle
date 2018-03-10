/*
 * Copyright 2018 the original author or authors.
 *
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
 */

package org.gradle.api.internal.tasks.testing;

import org.gradle.api.GradleException;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

public abstract class AbstractWorkerTestClassProcessor implements TestClassProcessor {
    private static final Logger LOGGER = Logging.getLogger(AbstractWorkerTestClassProcessor.class);

    @Override
    public void processTestClass(TestClassRunInfo testClassInfo) {
        try {
            Class<?> testClass = Class.forName(testClassInfo.getTestClassName(), false, Thread.currentThread().getContextClassLoader());
            LOGGER.debug("Executing test class {}", testClassInfo.getTestClassName());
            processTestClass(testClass);
        } catch (ClassNotFoundException e) {
            if (!(testClassInfo instanceof PreviousFailedTestClassRunInfo)) {
                throw new GradleException(String.format("Could not load test class '%s'.", testClassInfo.getTestClassName()), e);
            }
        }
    }

    @Override
    public void stopNow() {
        throw new UnsupportedOperationException("stopNow() should not be invoked on remote worker TestClassProcessor");
    }

    protected abstract void processTestClass(Class<?> testClass);
}

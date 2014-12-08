/*
 * Copyright 2014 Igor Maznitsa.
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
package com.igormaznitsa.jcp.usecases;

import com.igormaznitsa.jcp.JCPreprocessor;
import com.igormaznitsa.jcp.context.PreprocessorContext;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class ExternalGlobalDefFileTest extends AbstractUseCaseTest {

  @Override
  protected void tuneContext(PreprocessorContext context) {
    context.addConfigFile(new File(sourceFolder.getParent(),"list.cfg"));
  }

  @Override
  public void check(final PreprocessorContext context, final JCPreprocessor.PreprocessingStatistics stat) throws Exception {
    assertEquals(0,stat.getNumberOfCopied());
    assertEquals(1,stat.getNumberOfPreprocessed());
  }
  
}

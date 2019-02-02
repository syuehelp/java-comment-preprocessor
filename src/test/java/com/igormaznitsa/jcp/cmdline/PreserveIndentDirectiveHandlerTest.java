/*
 * Copyright 2002-2019 Igor Maznitsa (http://www.igormaznitsa.com)
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.igormaznitsa.jcp.cmdline;

import com.igormaznitsa.jcp.context.PreprocessorContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PreserveIndentDirectiveHandlerTest extends AbstractCommandLineHandlerTest {

  private static final PreserveIndentDirectiveHandler HANDLER = new PreserveIndentDirectiveHandler();

  @Override
  public void testThatTheHandlerInTheHandlerList() {
    assertHandlerInTheHandlerList(HANDLER);
  }

  @Override
  public void testExecution() throws Exception {
    final PreprocessorContext mock = prepareMockContext();

    assertFalse(HANDLER.processCommandLineKey("/Pi:", mock));
    assertFalse(HANDLER.processCommandLineKey("/PI ", mock));
    verify(mock, never()).setPreserveIndent(anyBoolean());

    assertTrue(HANDLER.processCommandLineKey("/PI", mock));
    verify(mock).setPreserveIndent(true);
    reset(mock);

    assertTrue(HANDLER.processCommandLineKey("/PI", mock));
    verify(mock).setPreserveIndent(true);
    reset(mock);
  }

  @Override
  public void testName() {
    assertEquals("/PI", HANDLER.getKeyName());
  }

  @Override
  public void testDescription() {
    assertDescription(HANDLER);
  }
}

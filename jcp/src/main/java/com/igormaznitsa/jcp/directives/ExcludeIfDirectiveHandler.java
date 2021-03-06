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

package com.igormaznitsa.jcp.directives;

import com.igormaznitsa.jcp.context.PreprocessingState;
import com.igormaznitsa.jcp.context.PreprocessorContext;

import javax.annotation.Nonnull;

import static com.igormaznitsa.meta.common.utils.Assertions.assertNotNull;

/**
 * The class implements the //#excludeif directive handler
 *
 * @author Igor Maznitsa (igor.maznitsa@igormaznitsa.com)
 */
public class ExcludeIfDirectiveHandler extends AbstractDirectiveHandler {

  @Override
  @Nonnull
  public String getName() {
    return "excludeif";
  }

  @Override
  @Nonnull
  public String getReference() {
    return "if argument is TRUE then the file will be ignored by preprocessor";
  }

  @Override
  public boolean isGlobalPhaseAllowed() {
    return true;
  }

  @Override
  public boolean isPreprocessingPhaseAllowed() {
    return false;
  }

  @Override
  @Nonnull
  public DirectiveArgumentType getArgumentType() {
    return DirectiveArgumentType.BOOLEAN;
  }

  @Override
  @Nonnull
  public AfterDirectiveProcessingBehaviour execute(@Nonnull final String string, @Nonnull final PreprocessorContext context) {
    final PreprocessingState state = context.getPreprocessingState();
    state.pushExcludeIfData(state.getRootFileInfo(), string, assertNotNull("'IF' stack is empty!", state.peekFile()).getLastReadStringIndex());
    return AfterDirectiveProcessingBehaviour.PROCESSED;
  }
}

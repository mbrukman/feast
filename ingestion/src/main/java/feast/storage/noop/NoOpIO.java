/*
 * Copyright 2018 The Feast Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package feast.storage.noop;

import feast.ingestion.transform.FeatureIO;
import feast.ingestion.transform.fn.Identity;
import feast.types.FeatureRowExtendedProto.FeatureRowExtended;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

public class NoOpIO {

  public static class Write extends FeatureIO.Write {

    @Override
    public PDone expand(PCollection<FeatureRowExtended> input) {
      input.apply(getName(), ParDo.of(new Identity(getName())));
      return PDone.in(input.getPipeline());
    }
  }
}

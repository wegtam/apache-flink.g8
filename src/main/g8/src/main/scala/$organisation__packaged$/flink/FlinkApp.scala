/*
 * Copyright (C) 2018  $organisation_name$
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package $organisation$.flink

import java.nio.file._
import java.util.UUID

import org.apache.flink.streaming.api.scala._

object FlinkApp {
  def main(args: Array[String]): Unit = {
    val streamEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    val f = Files.createTempFile("flink-", ".csv")
    val d = Files.deleteIfExists(f)

    val stream: DataStream[Int] =
      streamEnvironment.fromCollection(for (i <- 0 to 1000) yield i)
    val pairs: DataStream[(Int, UUID)] = stream.map(n => n -> UUID.randomUUID())
    pairs.writeAsCsv(f.toAbsolutePath.toString)

    val _ = streamEnvironment.execute()
  }
}

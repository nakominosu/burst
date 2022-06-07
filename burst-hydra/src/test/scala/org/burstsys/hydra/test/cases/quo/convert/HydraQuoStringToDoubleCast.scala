/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.hydra.test.cases.quo.convert

import org.burstsys.fabric.execution.model.result.group.FabricResultGroup
import org.burstsys.hydra.test.cases.support.HydraUseCase

object HydraQuoStringToDoubleCast extends HydraUseCase(1, 1, "quo") {

  /*
    override lazy val sweep = new B7FFFA8AE239745BEA49389BD1772429A
    override val serializeTraversal = true
  */

  override def analysisSource: String =
    s"""
       |hydra $analysisName() {
       |  schema quo
       |  frame $frameName  {
       |    cube user {
       |      limit = 999999
       |      dimensions {
       |        'cast':verbatim[double]
       |      }
       |    }

       |    user.sessions.events.parameters ⇒ {
       |      situ ⇒ {
       |        $analysisName.$frameName.'cast' = cast( value(user.sessions.events.parameters) as double )
       |        insert($analysisName.$frameName)
       |      }
       |    }
       |
       |  }
       |}
     """.stripMargin

  override def
  validate(implicit result: FabricResultGroup): Unit = {
    val r = result.resultSets(result.resultSetNames(frameName))
    assertLimits(r)

    val data = r.rowSet.map {
      row => row.cells(0).asDouble
    }.sorted

    data should equal(expected)
  }

  val expected: Array[Any] =

  Array(
    0.0, 0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, 19.0, 20.0,
    21.0, 22.0, 23.0, 25.0, 30.0, 34.0, 35.0, 36.0, 40.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0, 48.0, 49.0, 50.0, 55.0,
    60.0, 65.0, 70.0, 75.0, 80.0, 90.0, 92.0, 100.0, 105.0, 110.0, 115.0, 120.0, 125.0, 140.0, 150.0, 160.0, 175.0,
    180.0, 185.0, 190.0, 200.0, 210.0, 225.0, 240.0, 245.0, 250.0, 275.0, 300.0, 320.0, 325.0, 350.0, 375.0, 400.0,
    410.0, 425.0, 440.0, 450.0, 460.0, 500.0, 505.0, 525.0, 550.0, 560.0, 590.0, 600.0, 625.0, 640.0, 650.0, 675.0,
    700.0, 750.0, 770.0, 790.0, 800.0, 850.0, 900.0, 1000.0, 1050.0, 1060.0, 1100.0, 1120.0, 1150.0, 1190.0, 1200.0,
    1250.0, 1300.0, 1350.0, 1400.0, 1450.0, 1500.0, 1540.0, 1600.0, 1650.0, 1700.0, 1800.0, 1850.0, 1875.0, 2000.0,
    2100.0, 2250.0, 2300.0, 2375.0, 2380.0, 2400.0, 2500.0, 2600.0, 2700.0, 2800.0, 2900.0, 2950.0, 3000.0, 3200.0,
    3250.0, 3300.0, 3400.0, 3600.0, 3650.0, 3700.0, 3750.0, 4000.0, 4200.0, 4500.0, 4760.0, 4800.0, 5000.0, 5200.0,
    5800.0, 6000.0, 6250.0, 6400.0, 6800.0, 7200.0, 7400.0, 7500.0, 8000.0, 8400.0, 9000.0, 9600.0, 10000.0, 11345.0,
    12000.0, 12400.0, 12500.0, 14400.0, 15000.0, 16000.0, 16200.0, 18000.0, 19200.0, 20000.0, 24000.0, 25000.0,
    32000.0, 40000.0, 80000.0, 100000.0
  )

  Array(
    0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 10.0, 12.0, 13.0, 14.0, 15.0, 17.0, 18.0, 19.0, 20.0, 21.0, 25.0,
    27.0, 29.0, 30.0, 31.0, 32.0, 33.0, 34.0, 35.0, 37.0, 38.0, 39.0, 40.0, 41.0, 43.0, 46.0, 48.0, 49.0, 50.0, 51.0,
    52.0, 53.0, 54.0, 55.0, 56.0, 57.0, 58.0, 59.0, 60.0, 61.0, 62.0, 63.0, 128.0, 129.0, 130.0, 131.0, 132.0, 133.0,
    134.0, 135.0, 138.0, 139.0, 140.0, 141.0, 142.0, 143.0, 144.0, 145.0, 147.0, 151.0, 153.0, 155.0, 157.0, 158.0,
    159.0, 160.0, 161.0, 162.0, 163.0, 164.0, 165.0, 166.0, 167.0, 173.0, 174.0, 175.0, 176.0, 177.0, 178.0, 179.0,
    180.0, 181.0, 182.0, 184.0, 185.0, 186.0, 188.0, 189.0, 190.0, 191.0, 193.0, 194.0, 197.0, 198.0, 204.0, 208.0,
    209.0, 211.0, 215.0, 219.0, 221.0, 223.0, 224.0, 225.0, 226.0, 227.0, 228.0, 229.0, 238.0, 239.0, 241.0, 242.0,
    243.0, 244.0, 246.0, 248.0, 250.0, 252.0, 254.0, 255.0, 257.0, 258.0, 262.0, 272.0, 279.0, 283.0, 285.0, 287.0,
    289.0, 292.0, 306.0, 307.0, 310.0, 312.0, 314.0, 316.0, 318.0, 321.0, 343.0, 347.0, 356.0, 380.0, 385.0
  )

  Array(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0, 16.0, 17.0,
    18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0, 28.0, 29.0, 30.0, 31.0, 32.0, 33.0, 34.0, 35.0,
    36.0, 37.0, 38.0, 39.0, 40.0, 41.0, 42.0, 43.0, 44.0, 45.0, 46.0, 47.0, 48.0, 49.0, 50.0, 55.0, 60.0, 65.0,
    70.0, 75.0, 80.0, 85.0, 90.0, 92.0, 95.0, 100.0, 105.0, 110.0, 115.0, 120.0, 125.0, 135.0, 140.0, 145.0, 150.0,
    155.0, 160.0, 165.0, 170.0, 175.0, 180.0, 185.0, 190.0, 195.0, 200.0, 205.0, 210.0, 220.0, 225.0, 230.0, 235.0,
    240.0, 245.0, 250.0, 260.0, 265.0, 270.0, 275.0, 280.0, 285.0, 290.0, 295.0, 300.0, 310.0, 315.0, 320.0, 325.0,
    330.0, 335.0, 340.0, 345.0, 350.0, 360.0, 370.0, 375.0, 380.0, 385.0, 390.0, 400.0, 410.0, 420.0, 425.0, 435.0,
    440.0, 445.0, 450.0, 455.0, 460.0, 465.0, 470.0, 475.0, 480.0, 485.0, 490.0, 495.0, 500.0, 505.0, 520.0, 525.0,
    530.0, 535.0, 550.0, 554.0, 555.0, 560.0, 570.0, 575.0, 585.0, 590.0, 595.0, 600.0, 610.0, 615.0, 625.0, 635.0,
    640.0, 645.0, 650.0, 660.0, 670.0, 675.0, 680.0, 690.0, 700.0, 710.0, 720.0, 725.0, 730.0, 750.0, 760.0, 770.0,
    775.0, 780.0, 790.0, 800.0, 805.0, 810.0, 820.0, 825.0, 850.0, 860.0, 875.0, 880.0, 885.0, 890.0, 900.0, 925.0,
    940.0, 950.0, 960.0, 975.0, 980.0, 990.0, 1000.0, 1025.0, 1030.0, 1050.0, 1060.0, 1070.0, 1075.0, 1100.0, 1120.0,
    1125.0, 1135.0, 1140.0, 1150.0, 1175.0, 1185.0, 1190.0, 1200.0, 1220.0, 1250.0, 1260.0, 1265.0, 1270.0, 1275.0,
    1280.0, 1300.0, 1340.0, 1350.0, 1370.0, 1375.0, 1380.0, 1400.0, 1410.0, 1425.0, 1440.0, 1445.0, 1450.0, 1460.0,
    1475.0, 1500.0, 1520.0, 1525.0, 1540.0, 1550.0, 1575.0, 1595.0, 1600.0, 1625.0, 1650.0, 1665.0, 1700.0, 1710.0,
    1720.0, 1725.0, 1750.0, 1760.0, 1800.0, 1835.0, 1850.0, 1875.0, 1900.0, 1920.0, 1925.0, 1935.0, 1950.0, 1980.0,
    2000.0, 2025.0, 2035.0, 2050.0, 2070.0, 2100.0, 2125.0, 2130.0, 2140.0, 2150.0, 2170.0, 2175.0, 2190.0, 2200.0,
    2225.0, 2250.0, 2270.0, 2275.0, 2300.0, 2370.0, 2375.0, 2380.0, 2400.0, 2430.0, 2440.0, 2450.0, 2475.0, 2500.0,
    2525.0, 2550.0, 2580.0, 2600.0, 2625.0, 2640.0, 2680.0, 2700.0, 2750.0, 2760.0, 2800.0, 2820.0, 2850.0, 2900.0,
    2925.0, 2930.0, 2950.0, 3000.0, 3008.0, 3035.0, 3050.0, 3075.0, 3150.0, 3190.0, 3200.0, 3225.0, 3250.0, 3270.0,
    3275.0, 3300.0, 3350.0, 3375.0, 3400.0, 3420.0, 3440.0, 3450.0, 3480.0, 3500.0, 3600.0, 3650.0, 3700.0, 3750.0,
    3800.0, 3875.0, 3900.0, 3960.0, 4000.0, 4050.0, 4060.0, 4070.0, 4100.0, 4110.0, 4135.0, 4140.0, 4175.0, 4180.0,
    4200.0, 4225.0, 4250.0, 4300.0, 4340.0, 4350.0, 4400.0, 4455.0, 4500.0, 4550.0, 4600.0, 4650.0, 4680.0, 4700.0,
    4750.0, 4760.0, 4800.0, 4875.0, 4895.0, 4900.0, 5000.0, 5050.0, 5075.0, 5100.0, 5150.0, 5175.0, 5200.0, 5250.0,
    5300.0, 5400.0, 5500.0, 5520.0, 5600.0, 5625.0, 5750.0, 5800.0, 5850.0, 5900.0, 5910.0, 5950.0, 6000.0, 6070.0,
    6075.0, 6085.0, 6100.0, 6125.0, 6150.0, 6200.0, 6250.0, 6300.0, 6375.0, 6400.0, 6450.0, 6500.0, 6525.0, 6540.0,
    6550.0, 6600.0, 6675.0, 6700.0, 6750.0, 6800.0, 6825.0, 6900.0, 7000.0, 7100.0, 7125.0, 7200.0, 7300.0, 7400.0,
    7500.0, 7650.0, 7800.0, 7875.0, 7910.0, 8000.0, 8100.0, 8200.0, 8270.0, 8400.0, 8450.0, 8500.0, 8675.0, 8700.0,
    8750.0, 8800.0, 8850.0, 9000.0, 9100.0, 9135.0, 9170.0, 9200.0, 9225.0, 9250.0, 9450.0, 9500.0, 9600.0, 9675.0,
    9750.0, 9825.0, 9900.0, 10000.0, 10050.0, 10100.0, 10125.0, 10150.0, 10200.0, 10250.0, 10300.0, 10400.0, 10500.0,
    10600.0, 10665.0, 11000.0, 11200.0, 11235.0, 11250.0, 11345.0, 11435.0, 11460.0, 11500.0, 11600.0, 11800.0,
    11900.0, 12000.0, 12100.0, 12140.0, 12200.0, 12235.0, 12250.0, 12300.0, 12400.0, 12500.0, 12600.0, 12800.0,
    12900.0, 13000.0, 13050.0, 13080.0, 13100.0, 13145.0, 13200.0, 13500.0, 13650.0, 14000.0, 14300.0, 14400.0,
    14600.0, 14800.0, 15000.0, 15200.0, 15225.0, 15250.0, 15400.0, 15480.0, 15820.0, 16000.0, 16200.0, 16250.0,
    16400.0, 16795.0, 16800.0, 16825.0, 17000.0, 17100.0, 17125.0, 17235.0, 17400.0, 17500.0, 17600.0, 17700.0,
    17730.0, 17800.0, 18000.0, 18200.0, 18255.0, 18300.0, 18450.0, 18750.0, 19100.0, 19200.0, 19650.0, 19800.0,
    20000.0, 20250.0, 20300.0, 20475.0, 20800.0, 20825.0, 21000.0, 21235.0, 21300.0, 21400.0, 21500.0, 22000.0,
    22235.0, 22470.0, 22500.0, 23000.0, 23150.0, 23600.0, 23800.0, 24000.0, 24200.0, 24250.0, 24400.0, 24470.0,
    24500.0, 24600.0, 24800.0, 25000.0, 25200.0, 25300.0, 25600.0, 26000.0, 26200.0, 26400.0, 27000.0, 27300.0,
    27510.0, 27600.0, 28125.0, 28500.0, 28600.0, 29000.0, 30000.0, 30150.0, 30500.0, 30800.0, 31000.0, 31250.0,
    32000.0, 32480.0, 33750.0, 34200.0, 34250.0, 34300.0, 34470.0, 35000.0, 35200.0, 36000.0, 36100.0, 36120.0,
    36450.0, 36500.0, 37500.0, 38000.0, 38200.0, 40000.0, 40150.0, 40250.0, 42000.0, 42470.0, 42600.0, 43375.0,
    44000.0, 44470.0, 45000.0, 45200.0, 45600.0, 46300.0, 47000.0, 48000.0, 48800.0, 49200.0, 50000.0, 50250.0,
    51300.0, 52000.0, 52400.0, 52408.0, 54000.0, 55000.0, 55225.0, 57200.0, 58000.0, 60000.0, 60500.0, 61200.0,
    61700.0, 63000.0, 64000.0, 64100.0, 67410.0, 68940.0, 70400.0, 72850.0, 72900.0, 73000.0, 75000.0, 75600.0,
    75900.0, 80000.0, 81000.0, 83300.0, 83800.0, 84940.0, 90000.0, 90400.0, 94000.0, 95225.0, 96000.0, 100000.0,
    109500.0, 110450.0, 120000.0, 121250.0, 125000.0, 140800.0, 145700.0, 146000.0, 150000.0, 160000.0, 165000.0,
    168750.0, 180000.0, 180800.0, 182500.0, 192000.0, 192500.0, 200000.0, 240000.0, 246800.0, 250000.0, 300000.0,
    328500.0, 360000.0, 361600.0, 400000.0, 480000.0, 500000.0, 537500.0, 600000.0, 750000.0, 800000.0, 912500.0,
    1000000.0, 1200000.0, 1875000.0, 2000000.0, 5000000.0, 7500000.0
  )


}

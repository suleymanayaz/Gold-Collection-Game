## Altın Toplama Oyunu
Varsayılan (20x20) tahta üzerinde varsayılan(%40) altin ve bu altinlarin varsayılan(%20)'si kadar gizli altin bulunmaktadır.
A,B,C,D oyuncuları tahtanın 4 bir köşesinden başlayarak sırayla hedef belirleyip hareket ederek altınları toplamaya çalışır.
Main methodu olan Board sınıfının run edilmesiyle jframe penceresi acılır.
Açılan penecerenin sağ tarafı varsayılan olarak  ayarlanmış tahta boyutu,altin,gizli altin oranları ve Oyuncuların varsayılan olarak  verilmiş özellikleri bulunur.
Bu varsayılan değerleri değiştirdikten sonra ayarla butonuna basılması gerekiyor.
Oyun çalıştır tuşuna basılmasıyla başlamaktadır.
Oyun sonlandığında oyuncuların hareket,altin miktarlari Result_PLAYER_NAME.txt dosyalarına yazdırılıyor.
OyunUI sınıfında oyuncuların hareket methodlarında (Thread.sleep(0)) ifadesi 0 olduğu için hızlı bir şekilde sonuca ulaşılıyor.
(Thread.sleep(0)) ifadesinideki 0'ı değiştirerek  çalışma zamanının uzatabilirsiniz.


## Gold-Collection-Game
The default (20x20) board has the default (40%) gold and the default (20%) of these gold is hidden gold.
Players A, B, C, D start from 4 corners of the board and try to collect gold by setting targets and moving in turn.
Running the Board class, which is the Main method, opens the jframe window.The right side of the pop-up window has the default board size, gold, hidden gold proportions, and Player attributes set by default.
After changing these default values, the set button should be pressed.
The game starts by pressing the run button.
When the game is over, the players' move, gold amounts are written to Result_PLAYER_NAME.txt files.
Since the expression (Thread.sleep (0)) is 0 in the movement methods of the players in the OyunUI class, a quick result is reached.
You can extend the runtime by changing the 0 in (Thread.sleep (0)) expression.


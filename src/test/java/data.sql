DROP TABLE IF EXISTS roles CASCADE;

--権限
CREATE TABLE roles(
id serial PRIMARY KEY,
role VARCHAR(30) NOT NULL UNIQUE
);

--roles
INSERT INTO roles (role) VALUES ('admin'),('user');


-- ユーザー
drop table if exists users cascade;

create table users (
 id serial primary key
 , name varchar(100) not null
 , email varchar(100) not null unique
 , password text not null
 , zipcode varchar(8) not null
 , address varchar(200) not null
 , telephone varchar(15) not null
 , role integer not null
 , FOREIGN KEY (role) REFERENCES roles(id)
) ;

-- 管理者ユーザ
INSERT INTO users (name, email, password, zipcode, address, telephone, role) VALUES('テスト1','test1@example.com', 'pass1','011-1111', '住所1','111-1111-1111',1);
-- 一般ユーザ
INSERT INTO users (name, email, password, zipcode, address, telephone, role) VALUES('テスト2','test2@example.com', 'pass2','022-2222', '住所2','222-2222-2222',2);
-- 一般ユーザ（カートに商品なし）
INSERT INTO users (name, email, password, zipcode, address, telephone, role) VALUES('テスト3','test3@example.com', 'pass3','033-3333', '住所3','333-3333-3333',2);

-- 商品
drop table if exists items cascade;

create table items (
    id serial primary key
  , name text not null
  , description text not null
  , price_m integer not null
  , price_l integer not null
  , image_path text not null
  , deleted boolean default false not null
) ;

--items
insert into items (name,description,price_m,price_l,image_path) values('とんこつラーメン', '創業当時から今に引き継ぐとんこつラーメンの本流であり、原点の味。18時間の調理と、丸1日の熟成を経て、とんこつの旨味を極限まで抽出した豊かで香り高いシルキーなスープに、博多らしい細麺がマッチします。', 700, 800, '1.jpg');
insert into items (name,description,price_m,price_l,image_path) values('赤ラーメン', '自家製の香味油と辛みそを加えることで、一杯のラーメンの中でいくつもの味の奥行きと調和を楽しめます。白丸が正統派のとんこつラーメンならば、赤丸新味は豚骨ラーメンの可能性を広げた“革新派”。 コクと深みを追求した、自信作です。', 750, 850, '2.jpg');
insert into items (name,description,price_m,price_l,image_path) values('からか麺', '博多絹ごしとんこつをベースに、豆板醤や甜麺醤などを独自に配合した肉味噌を大胆にトッピング。山椒などのスパイスを効かせた自家製ラー油が全体をピリリとまとめあげ、中太のストレート麺がうま味と辛味を余すところなくすくいあげます。1989年に大名本店で登場以来、進化を続ける根強い人気の一杯です。', 800, 900, '3.jpg');
insert into items (name,description,price_m,price_l,image_path) values('かさね味Special', '2001年、本店限定メニューとして誕生。とんこつと鶏ガラを素材に、それぞれの旨みを抽出しながら絶妙なバランスで合わせた鶏豚骨スープは、さっぱりとしながらも深みがあり、加水率の高い中太麺を引き立てています。', 900, 1000, '4.jpg');
insert into items (name,description,price_m,price_l,image_path) values('百福元味', 'ラーメンに生涯を捧げた麺翁、日清食品創業者・安藤百福氏を想い、一風堂店主・河原成美が手掛けた醤油ラーメン。 国産の丸鶏を使用したスープ、国産小麦を2種類使用した平打ちちぢれ麺、自家製のごぼう香油など、こだわり抜かれた一杯です。', 980, 1080, '5.jpg');
insert into items (name,description,price_m,price_l,image_path) values('川越とんこつ醤油', '埼玉県・川越のラーメン店と醤油メーカーが中心となって行っている「川越醤油ラーメン」プロジェクトに一風堂も参加！松本醤油商店さんの「はつかり醤油」と川越産の小松菜を一風堂のとんこつスープを合わせた「川越とんこつ醤油」です。', 880, 980, '6.jpg');
insert into items (name,description,price_m,price_l,image_path) values('元祖・白丸元味／元祖・赤丸新味', '1985年に創業した博多 一風堂の1号店である大名本店限定メニュー。創業当時のトロリとしたポタージュのようなスープを再現し、通常よりも一段と濃厚な白丸/赤丸に仕上げています。全国のラーメンファン、観光客の方が、この味を求めて足を運んでくださいます。', 900, 1000, '7.jpg');
insert into items (name,description,price_m,price_l,image_path) values('鶏とんこつ麺', '関西のラーメン激戦区で修行を積んだ親父による珠玉の一品。濃厚な鶏の凝縮されたコクのあるスープの旨味と極太麺に海苔の風味が上品かつ風情を醸し出した自信作。', 800, 900, '8.jpg');
insert into items (name,description,price_m,price_l,image_path) values('辛味噌ちゃあしゅう麺', '九州で伝統的に伝わる発酵手法で作られた秘伝味噌をふんだんに使用したコクのある味噌ラーメン。チャーシューは低温調理で豚本来の旨味をぎゅっと閉じ込めた贅沢な一杯。', 820, 920, '9.jpg');
insert into items (name,description,price_m,price_l,image_path) values('追い鰹チャーシュー', '関西のラーメンランキングでNo.1をとったこともある名店で仕込まれた味。香り高い追い鰹でとられたスープはただ飲むだけでなく、添えられた車麩に浸しても楽しめる。ここでしか食べられない極上の一杯。', 1050, 1100, '10.jpg');
insert into items (name,description,price_m,price_l,image_path) values('鶏白湯坦々麺', '鶏の部位でも上質な部位のみを厳選し、旨味を閉じ込めた白湯スープ。10種類のスパイスが組み合わさった爽やかな辛さとナッツの甘味が香ばしさを引き立てた一杯。', 980, 1080, '11.jpg');
insert into items (name,description,price_m,price_l,image_path) values('貝出汁らーめん原点', '人気の魚「貝」系らーめん。あさりやしじみの貝の旨味を閉じ込め、淡口醤油であっさり仕上げている。', 900, 1000, '12.jpg');
insert into items (name,description,price_m,price_l,image_path) values('澄み切った醤油らーめん', '喉越しの良さを突き詰めた王道醤油ラーメン。すっきりとした見た目とは裏腹にじっくりのコクのある最後までスープを飲み干したくなる一杯。', 950, 1050, '13.jpg');
insert into items (name,description,price_m,price_l,image_path) values('煮干しつけ麺', '煮干しでとられた醤油ベースのスープが喉越しの良いもっちりとした麺の味を引き立てるつけ麺。', 750, 850, '14.jpg');
insert into items (name,description,price_m,price_l,image_path) values('旨辛味噌麺', '味噌ラーメンの常識を変える一杯。濃厚かつコクと深みのあるスープ、小麦の味がこみ上げる極太麺、ジューシーなチャーシューが胃袋を鷲掴みにする。', 680, 800, '15.jpg');
insert into items (name,description,price_m,price_l,image_path) values('まぜ麺Gorgeous4', '食欲をそそるエスニックでスパイシーな一杯。しっかり混ぜて広がるまぜ麺に最後は追い飯をすれば、1度だけでなく2度楽しめるお得感のあるラーメンです。', 780, 880, '16.jpg');
insert into items (name,description,price_m,price_l,image_path) values('台湾まぜそば', 'ピリ辛!!の台湾まぜそば汗をジンワリとかく程度の辛さで魚粉の香りが引き立ち、辛く味付けした挽肉が非常にマッチした一品。タレが残ったら追いめしが楽しめます。', 1200, 1300, '17.jpg');
insert into items (name,description,price_m,price_l,image_path) values('真・澄み切った塩らーめん', '岩塩でキリッとさせた鶏スープがまとまりがある仕上がり。透明度の高いスープは旨味が凝縮された丁寧な一杯。何度でも食べたくなる味のラーメンです。', 990, 1090, '18.jpg');


-- トッピング
drop table if exists toppings cascade;

create table toppings (
  id serial primary key
  , name text not null
  , price_m integer not null
  , price_l integer not null
) ;

--toppings
insert into toppings (name,price_m,price_l) values('チャーシュー', 200, 300);
insert into toppings (name,price_m,price_l) values('煮たまご', 200, 300);
insert into toppings (name,price_m,price_l) values('メンマ', 200, 300);
insert into toppings (name,price_m,price_l) values('のり', 200, 300);
insert into toppings (name,price_m,price_l) values('もやし', 200, 300);
insert into toppings (name,price_m,price_l) values('ほうれん草', 200, 300);
insert into toppings (name,price_m,price_l) values('車麩', 200, 300);
insert into toppings (name,price_m,price_l) values('バター', 200, 300);
insert into toppings (name,price_m,price_l) values('白髪ねぎ', 200, 300);
insert into toppings (name,price_m,price_l) values('紫たまねぎ', 200, 300);
insert into toppings (name,price_m,price_l) values('うずら煮卵', 200, 300);
insert into toppings (name,price_m,price_l) values('薫製たまご', 200, 300);
insert into toppings (name,price_m,price_l) values('つみれ', 200, 300);
insert into toppings (name,price_m,price_l) values('ワンタン', 200, 300);
insert into toppings (name,price_m,price_l) values('ザーサイ', 200, 300);
insert into toppings (name,price_m,price_l) values('大トロチャーシュー', 200, 300);
insert into toppings (name,price_m,price_l) values('太麺に変更', 200, 300);
insert into toppings (name,price_m,price_l) values('追い飯', 200, 300);


--注文
drop table if exists orders cascade;

create table orders (
  id serial primary key
  , user_id integer not null
  , status integer not null
  , total_price integer not null
  , order_date date
  , destination_name varchar(100)
  , destination_email varchar(100)
  , destination_zipcode varchar(8)
  , destination_address varchar(200)
  , destination_tel varchar(15)
  , delivery_time timestamp
  , payment_method integer
  ) ;

--orders
--管理者未注文
INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method)
VALUES(1,0,0,null,null,null,null,null,null,null,null);
--管理者注文済み
INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method)
VALUES(1,1,0,'1111-01-01','宛先1','test1@example.com','111-1111','住所1','111-1111-1111','1111-11-11',1);
--一般ユーザ未注文
INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method)
VALUES(2,0,0,null,null,null,null,null,null,null,null);
--一般ユーザ注文済み
INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method)
VALUES(2,1,0,'2222-02-02','テストユーザ','test2@test.co.jp','222-2222','住所2','222-2222','2022-02-22',1);
--一般ユーザ(カート内商品なし)
INSERT INTO orders(user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method)
VALUES(3,0,0,null,null,null,null,null,null,null,null);


-- 注文商品
drop table if exists order_items cascade;

create table order_items (
  id serial primary key
  , item_id integer not null
  , order_id integer not null
  , quantity integer not null
  , size varchar(1)
) ;

--order_items
--rakunoo未注文
INSERT INTO order_items (item_id, order_id, quantity, size) VALUES(1,1,1,'M'),(2,1,2,'L'),(3,1,3,'M'),(4,1,4,'L'),(5,1,1,'L');
--rakunoo注文済み
INSERT INTO order_items (item_id, order_id, quantity, size) VALUES(6,2,1,'M'),(7,2,2,'L'),(8,2,3,'M'),(9,2,4,'L'),(10,2,1,'L');
--テストユーザ未注文
INSERT INTO order_items (item_id, order_id, quantity, size) VALUES(1,3,1,'M'),(2,3,2,'L'),(3,3,3,'M'),(4,3,4,'L'),(5,3,1,'L');
--テストユーザ注文済み
INSERT INTO order_items (item_id, order_id, quantity, size) VALUES(6,4,1,'M'),(7,4,2,'L'),(8,4,3,'M'),(9,4,4,'L'),(10,4,1,'L');
-- INSERT INTO order_items (item_id, order_id, quantity, size) VALUES(6,4,1,'M');

-- 注文トッピング
drop table if exists order_toppings cascade;

create table order_toppings (
  id serial primary key
  , topping_id integer not null
  , order_item_id integer not null
) ;

--order_toppings
--rakunoo未注文
INSERT INTO order_toppings (topping_id,order_item_id) VALUES(1,1),(2,1),(3,2),(4,2);
--rakunoo注文済み
INSERT INTO order_toppings (topping_id,order_item_id) VALUES(5,6),(6,6),(7,7),(8,7);
--テストユーザ未注文
INSERT INTO order_toppings (topping_id,order_item_id) VALUES(9,11),(10,11),(11,12),(12,12);
--テストユーザ注文済み
--INSERT INTO order_toppings (topping_id,order_item_id) VALUES(13,16),(14,16),(15,17),(16,17);
INSERT INTO order_toppings (topping_id,order_item_id) VALUES(13,16);

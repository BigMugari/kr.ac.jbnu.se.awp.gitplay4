1.List a = new ArrayList() or new LinkedList();
a.add(넣을거1);
a.add(넣을거2);
a를 메소드의 List 파라미터에 넣으면 된다.

2.select메소드의 반환 값은 List로 List안에 Index가 Map 타입 개체이다.
그런데 List객체의 get(i)메소드는 반환값이 Object타입이므로 Map으로 다운캐스팅을 해야 한다. 
example) List user = db.select(columns, tables, conditions, values);
Map userInfo = (Map)user.get(0);

메소드(파라미터) : 반환값 { SQL문 예시 }
insert(String a, List b) : boolean { insert into a values( b ) }
insert(String a, List b,List c) : boolean { insert into a( b ) values( c ) }
delete(String a) : boolean { delete from a }
delete(List a) : boolean { delete from a }
delete(List a, String b, List c) : boolean { delete from a where b } 값은 c
select(String a) : List<Map> { select * from a }
select(String a, String b) : List<Map> { select a from b }
select(List a, String b) : List<Map> { select a from b }
select(List a, List b, String c, String d) : List<Map> { select a from b where c } 값은 d
select(List a, List b, String c, List d) : List<Map> { select a from b where c } 값은 d

update는 구현 예정

DB이중조작어 아직 사용못함
VIEW 아직 사용 불가 

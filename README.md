1. User:
   1. id
   2. Name
   3. Status (ACTIVE, INACTIVE)

2. Payment:
   1. paymentId
   2. userId
   3. amount
   4. message

<h2> Возможные действия </h2>
<h4>Условия, которые можно проверить</h4>

1. <h3> Пользователя можно найти по id </h3>
   <p>id не должно быть null</p>

2. <h3> Оплату можно найти по id </h3>
    <p>id не должно быть null</p>

3. <h3> Можно получить все платежы </h3>
4. <h3> Можно сохранить платёж</h3>
   <p>Платёж не должен быть null <br>
   Нельзя сохранить платёж с id, которое уже есть в базе
   </p>
5. <h3> Можно редактировать сообщение платежа</h3>
   <p>Платёж не должен быть null <br>
   Сообщение не должно быть null</p>
6. <h3>Можно создать платёж для пользователя</h3>
    <p>Id пользователя не должен быть null <br>
    Сумма платежа не может быть null<br>
   Сумма платежа не может быть меньше или равна 0<br>
   Пользователь должен существовать в базе данны<br>
   Статус пользователя должен быть активным</p>
7. <h3> Можно изменить сообщение платежа</h3>
     <p>Id платёжа не должено быть null <br>
   Сообщение не должно быть null</p>
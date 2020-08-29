import com.message.dto.MessageDTO;
import com.message.common.enums.ActionTypeEnum;
import com.message.common.exception.BusinessException;
import com.message.service.ConsumerService;
import com.message.service.ProducerService;
import com.message.common.util.Utils;


public class Main {

    public static void main(String[] args) {

        //start random message sender
        startRandomMessageSender();
//        Utils.delay(5);
        //start application
        new ConsumerService().initMessageSenderConsumer();
    }


    static void startRandomMessageSender(){
        Runnable runnable = new Runnable() {

            public void run() {
                sendRandomMessage();
            }

            void sendRandomMessage(){
                int counter = 1;
                while(true){
                    MessageDTO messageDTO = createRandomMessage(counter);
                    boolean isAdded = false;
                    try{
                        isAdded = new ProducerService().addMessage(messageDTO);
                    }
                    catch (BusinessException e){

                    }
                    if(counter%2300 == 0)
                        Utils.delay(2);
                    if(isAdded)
                        System.out.println("+"+counter+messageDTO.getActionType());
                    counter++;
                    if(counter%10000 == 0)
                        Utils.delay(20);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private static MessageDTO createRandomMessage(int counter){
        String text = "" + counter;
        String address;
        int rand = (int)((Math.random()*10)%3);
        ActionTypeEnum action;
        if(rand == 0){
            action = ActionTypeEnum.SMS;
            if(counter%2 == 0)
                address = "123345456";
            else
                address = "+123123123";
        }
        else if(rand == 1){
            action = ActionTypeEnum.EMAIL;
            address =  "address"+counter+"@address.com";
        }
        else{
            action = ActionTypeEnum.PUSH;
            address = "123123123asdasd";
        }
                    if(counter%5 == 0)
                        address = "invalid|";
        return new MessageDTO(address,action,text);
    }



}

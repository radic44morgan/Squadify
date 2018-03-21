/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

/**
 *
 * @author morganradic
 */
public class TestPackage
{

    public static void main(String[] args)
    {

        System.out.println("Creating master user");
        MasterUserModel user1 = new MasterUserModel("testuser");
        System.out.println("User created");
        System.out.println("Creating QueueController");
        QueueController controller = new QueueController();
        System.out.println("Controller created");
        System.out.println("Creating queue");
        user1.setQueueCode(controller.createQueue(user1));
        System.out.println("Queue created.");
        System.out.println("Number of queues: " + controller.getQueues().size());
        System.out.println("Creating participate user");
        UserModel user2 = new UserModel("participate");
        System.out.println("Adding user to queue");
        if (controller.addUserToQueue(user2, user1.getCode()))
        {
            System.out.println("User added");
            user2.setQueueCode(user1.getCode());
        }
        else
            System.out.println("Failed to add user");
    }

}

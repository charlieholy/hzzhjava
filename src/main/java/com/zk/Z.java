package com.zk;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;

public class Z {
    public static void main(String args[]){
        System.out.println("asd");
        String CONNECT_ADDR = "127.0.0.1:2181";
        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR), 10000);

        String path = "/zk-test";
        // 注册子节点变更监听（此时path节点并不存在，但可以进行监听注册）
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("路径" + parentPath +"下面的子节点变更。子节点为：" + currentChilds );
            }
        });

        // 递归创建子节点（此时父节点并不存在）
        zkClient.createPersistent("/zk-test/a2",true);
        try {
            Thread.sleep(5000);
        }catch (Exception e){

        }

        System.out.println(zkClient.getChildren(path));

        return;
    }
}

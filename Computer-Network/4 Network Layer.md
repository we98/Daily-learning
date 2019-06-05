## Network Layer
### Overview
***
1. Two key network-layer functions
    1. forwarding
    - move packets from router's input to appropriate router's output
    - process of getting through single interchange
    2. routing
    - determine route taken by packets from source to destination using routing algorithms
    - process of planning trip from source to destination
***
2. Network layer: data plane and control plane
    1. data plane
    - local, per-router function(forwarding)
    - determines how datagram arriving on router input port is forwarded to router output port
    2. control plane
    - network-wide logic
    - determines how datagram is routed among routers from source to destination
    - two approaches to implement the function of control plane
      - implemented in routers(traditional routing algorithms, individual routing algorithms components in each router interact in the control plane)
      - implemented in remote severs(software-defined networking, SDN, a distinct(remote) controller interacts with local control agents(CAs), it is logical centralized)
***
3. Network layer service models
    1. guaranteed delivery
    2. guaranteed delivery with a delay upper bound
    3. ordered packet delivery
    4. guaranteed loss
    5. guaranteed bandwidth
    6. guaranteed timing
    7. security
    - however, the network layer of Internet(best-effort) supports none of them
    - ATM supports some of them, see the following picture
    ![network_layer_service_model](pictures_network_layer\network_layer_service_model.png)
***
4. The router architecture overview
    1. the control pane consists of routing processor, the function of it is implemented by software, operates in millisecond time frame
    2. the data pane consists of router input ports, high-speed switching fabric and router output ports, for the reason that the input link may be with a high speed such G bits, the data pane must be implemented by hardware, operates in nanosecond time frame.
    ![router_architecture](pictures_network_layer\router_architecture.png)
***
5. The data pane structure of router overview
    1. router input ports(three modules and three functions)
    - line termination(physical layer, bit-level reception)
    - link layer protocol(unpacking)
    - lookingup, forwarding and queueing
    ![input_port_architecute](pictures_network_layer\input_port_architecute.png)
    2. high-speed switching fabric(three types, memory, bus or crossbar)
    ![switching_fabric_architecture](pictures_network_layer\switching_fabric_architecture.png)
    3. router ouput ports
    - datagram queueing
    - link layer protocol(encapsulation)
    - line termination
    ![output_port_architecture](pictures_network_layer\output_port_architecture.png)
***
6. Accoring to the functions of protocols, the protocols of network layer can be divided to three types, they are the main components of network layer
    1. routing protocols(for path selection, RIP, OSPF, BGP)
    2. IP protocol(addressing conventions, datagram format, packet handling conventions)
    3. ICMP protocol(error reporting, router "signaling")
    ![protocols_in_network_layer](pictures_network_layer\protocols_in_network_layer.png)
    ![protocols_structure](pictures_network_layer\protocols_structure.png)
### The Data Plane
1. IPv4 datagram format
    1. header(20 bytes or more)
    - version(4 bit)
    - header length(for the existence of options, the variable must be specified, 4 bits)
    - type of service(TOS)
    - datagram length(header + data, 16 bits, the maximum length is 65535 in theory, but little datagram is longer than 1500 bytes)
    - identifier, flag, fragment offset(totally 32 bits, related with fragmentation)
    - time to live(TTL, max remaining hops, decremented at each router)
    - upper layer(determines which transport layer protocol will be delivered, tcp or udp or others)
    - header checksum(check the header only, each 16 bits to add, changed at each router, for the reason that TTL is changed at each router)
    - 32 bits source IP
    - 32 bits destination IP
    - options(mostly without)
    2. data(TCP or DUP or others such as ICMP)
    ![IPv4format](pictures_network_layer\IPv4format.png)
***
2. IP fragmentation and reassembly
    1. With different link types and different MTUs, large IP datagram maybe divided at some routers
    - in the original IP datagram, identifier=x, flag=0, offset=0
    - the fragments have the same identifier with the original IP datagram, except the last fragment, other fragments' flag are 1, offset is in 8-bytes
    ![ip_fragmentation](pictures_network_layer\ip_fragmentation.png)
    2. Reassembly only at the final destination
***
3. DHCP(Dynamic Host Configuration Protocol) dynamically get address from DHCP sever
    1. it is "plug-and-play"
    2. steps
    - host broadcasts "DHCP discover" message
    - DHCP severs respond with "DHCP offer" messages
    - host chooses an IP address from one of the messages and broadcast "DHCP request" message
    - one of the DHCP  sever sends "DHCP ack" to that host
    3. the information DHCP sever returns
    - address of first-hop router for client
    - name and IP address of DNS sever
    - network mask
***
4. NAT, network address translation
    1. In NAT translation table, (source IP address, port #) to (NAT IP address, new port #) is added
***
5. IPv6
    1. motivation
    - 32-bit address space soon to be completely allocated
    - new header format helps speed processing/forwarding
    - new header changes to facilitate QoS
    2. the main changes in IPv6 header format
    - expanded address capacity
    - fixed-length 40 bytes header
    - flow and priority
    3. the headers of IPv6 format
    - version
    - traffic class(like TOS)
    - flow label
    - payload length(data length)
    - next header(like upper layer)
    - hop limit(like TTL)
    - source IP and destination IP
    4. 
### The Control Plane










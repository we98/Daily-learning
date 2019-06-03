## Transport Layer
1. Transport layer service and protocols in Internet
    1. The service transport layer must provide
    - provide logical communication between app processes running on different hosts
    - multiplexing and demultiplexing
    - error check
    2. UDP
    - only provides multiplex, demultiplexing and error check, based on the "best-effort delivery service" IP protocol
    - it is not reliable like IP
    - no congestion control, the data can be transferred at any rate apps provide
    3. TCP
    - Provide reliable data transfer. Based on flow control, sequence number, ack and alarm, TCP ensures correct and ordered data transfer from source to destination.
    - Provide congestion control. It is the service provided to the all Internet rather than the processes between two hosts. It aims to equal the shared network link bandwidth for each connection
    - connection setup
    4. Services not provided in Internet
    - delay guarantee
    - bandwidth guarantee
***
2. Multiplexing and Demultiplexing
    1. Multiplexing: handle data from multiple sockets, add transport header
    2. Demultiplexing: use header info to deliver segement to correct socket
    3. Demultiplexing principles:
    - each datagram has source IP address and destination IP address
    - each datagram carries one transport layer segment
    - each segment has source port and destination port
    - host uses IP address & port number to direct segment to appropriate socket
    4. Connectionless demultiplexing
    - the socket is identified by the destination IP and destination port
    - IP datagram with same port but different source will be directed to the same socket
    5. Connection oriented demultiplexing
    - the socket is identified by 4-tuple: source IP, source port, destination IP and destination port
    - even if the connections are from the same source host but with different port, the socket is different
    - for web severs, every non-persistent HTTP connection have different socket for each request
***
3. UDP services
    1. UDP provides multiplexing and demultiplexing and error check. It adds the source port(2 bytes) and destination port(2 bytes) and other 2 field(4 bytes) to form UDP segment
    2. Why UDP is fit for some apps?
    - More detailed application layer control on when and what data to send(no congestion control, UDP can blast away as fast as desired)
    - No need to set up connection, which makes it faster, such as DNS
    - No connection setup means it does not need to maintain some arguments on the sever, which means more active clients can be supported.
    - The header size of segment is 8 bytes smaller than 20 bytes of TCP segment.
***
4. UDP segment structure
    1. segment header, consists of 8 bytes and 4 fields
    - source port(2 bytes)
    - destination port(2 bytes)
    - length(2 bytes): the length of segment header and segment application data, in byte
    - checksum(2 bytes): check error in all UDP segment and some fields of IP header(but in the following, the checksum is treated to check the correctness of UDP segment)
    2. Application data
    - for HTTP, it is request or response in application layer
***
5. Principles of reliable data transfer










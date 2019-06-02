## Introdcution
1. What's the Internet?
    1. "nuts and bolts" view
    - billions of connected computing devices(hosts/end systems -- running network apps)
    - communication links(fiber, copper, radio, satellite -- they are different in transmission rate, bandwidth)
    - packet switches(routers and switches  -- forward packets)
    - protocols(TCP, IP, HTTP -- control sending and receiving messages)
    - Internet standards(specified by RFC, maintained)
    2. a service view
    - infrastructure that provides service to applications
    - provides programming interface to apps(hooks that allow sending and receiving app programs to connect to Internet)
***
2. What's a protocol?
- protocols define format, order of messages send and received among network entities, and actions taken on message transmission, receipt
***
3. Network edge
    1. access networks(the physical link connecting the end system to its edge router)
    - access technology (or access type or access environment)
        - home access(DSL -- dedicated and frequency division multiplexing, cable network -- homes share access network to cable headend, unlike DSL,  which has dedicated access to central office)
        - enterprise access(Ethernet -- wired LAN and WiFi -- wireless LAN)
        - wide-area wireless access(3G, 4G and LTE)
    - physical media
        - guided media(signals propagate in solid media, copper, fiber, coax)
        - unguided media(signals propagate freely, radio)
***
4. Network core
    1. packet switching(forward packets from one router to another, each packet transmitted at full link capacity)
    - store-and-forward
        - entire packet must  arrive at router before it can be transmitted on next link
        - therefore, there exists transmission delay due to the mechanism, the delay refers that the time from source host starts sending to destination host completely receives. If there is one packet of L bits, N links and the rate is R, the totally delay is N\*L/R. If there are n packets, the delay is N\*L/R + (n-1)\*L/R
    - queueing delay and loss
      - occurs when arrival rate exceeds the transmission rate
      - packets can be dropped if buffer fills up
    - two key network-core functions
      - routing: determines source-destination route taken in packets by routing algorithms
      - forwarding: move packets from router's input to appropriate router output
    2. circuit switching(dedicated resource, circuit segment idle if not used by call, common used in traditional telephone networks)
    - FDM(frequency-division multiplexing)
    - TDM(time-division multiplexing),
    - the transmission time has no relationship with the number of links, for example, suppose the network has 24-time-sliced TDM, and its bandwidth is 1.536Mbps. And we have 640000-bits file, the bandwidth of each link is 1.536Mbps/24=64kbps, and the construction of link is 500ms, so the total time is 10.5s, which has no relationship with the number of links, no matter it is 10 or 100
    3. packet switching versus circuit switching
    - packet switching allows more users to use network
    - packet switching is great for bursty data, because it is resource sharing and simpler, but may suffer from congestion, it is with packet delay and loss.
***
5. Delay, loss, throughput in networks
    1. four sources of packet delay d(nodal) = d(transmission) + d(propagation) + d(processing) + d(queueint)
    - processing: check bit errors, determine output link
    - queueing: time waiting at output link for transmission, depends on congestion level of router
    - transmission: L/R
    - propagation: d/s
    2. queueing delay
    - R, link bandwidth L, packet length a, average packet arrival rate  La/R~0 small ->1 large >1 average delay infinite
    3. throughput: rate (bits/time unit) at which bits transferred between sender/receiver, T=F/min{Rs, Rc}
***
6. Why layering?
    1. explicit structure allows to identify relationship of complex system's pieces
    2. modularization eases maintenance, updating of system
***
7. Internet protocol stack and ISO/OSI reference model
    1. Internet protocol stack
    - Application layer(HTTP, FTP, SMPT, DNS)
    - Transmission layer(TCP, UDP)
    - Network layer(IP)
    - Link layer(Ethernet, 802.111/WiFi, PPP)
    - Physical layer
    2. ISO/OSI reference model, base on the Internet protocol stack, the Application layer is divided into three part:
    - Application layer
    - Presentation layer
    - Session layer
